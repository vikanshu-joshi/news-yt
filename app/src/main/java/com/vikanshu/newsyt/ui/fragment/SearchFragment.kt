package com.vikanshu.newsyt.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikanshu.newsyt.Constants
import com.vikanshu.newsyt.R
import com.vikanshu.newsyt.databinding.FragmentSearchBinding
import com.vikanshu.newsyt.model.Filters
import com.vikanshu.newsyt.ui.adapter.ArticlesAdapter
import com.vikanshu.newsyt.ui.viewmodels.NewsViewModel
import com.vikanshu.newsyt.utility.NewsUtility
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class SearchFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: ArticlesAdapter
    private lateinit var filters: Filters
    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        adapter = ArticlesAdapter(requireContext(),
            onLoadMore = {
                viewModel.querySearch(
                    filters.apply {
                        page += 1
                    }
                )
            })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipFilterCategory.setOnClickListener(this)
        binding.chipFilterSortby.setOnClickListener(this)
        binding.chipFilterFrom.setOnClickListener(this)
        binding.chipFilterTo.setOnClickListener(this)

        binding.rvSearch.apply {
            adapter = this@SearchFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getSearchResults().observe(this, {
            if (binding.srlSearch.isRefreshing) binding.srlSearch.isRefreshing = false
            adapter.submitList(it.second)
            binding.filters = it.first
            filters = it.first
        })
        binding.srlSearch.setOnRefreshListener {
            binding.srlSearch.isRefreshing = true
            viewModel.querySearch(filters)
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                R.id.chip_filter_category -> openCategorySelectionDialog()
                R.id.chip_filter_sortby -> openSortBySelectionDialog()
                R.id.chip_filter_from -> openFromDatePickerDialog()
                R.id.chip_filter_to -> openToDatePickerDialog()
            }
        }
    }


    private fun openCategorySelectionDialog() {
        val alert = AlertDialog.Builder(requireContext())
        val categories = Constants.CATEGORIES.keys.toTypedArray()
        var currentSelection = categories.indexOf(filters.topic)
        alert.setSingleChoiceItems(
            categories,
            currentSelection
        ) { _, index ->
            currentSelection = index
        }
        alert.setTitle("Select News Category")
        alert.setPositiveButton("OK") { _, _ ->
            viewModel.querySearch(filters.apply {
                topic = categories[currentSelection]
            })
        }
        alert.setNegativeButton("Cancel") { _, _ ->
        }
        alert.show()
    }

    private fun openSortBySelectionDialog() {
        val alert = AlertDialog.Builder(requireContext())
        val sortby = Constants.SORTBY.keys.toTypedArray()
        var currentSelection = sortby.indexOf(filters.sortBy)
        alert.setSingleChoiceItems(
            sortby,
            currentSelection
        ) { _, index ->
            currentSelection = index
        }
        alert.setTitle("Sort By")
        alert.setPositiveButton("OK") { _, _ ->
            viewModel.querySearch(filters.apply {
                sortBy = sortby[currentSelection]
            })
        }
        alert.setNegativeButton("Cancel") { _, _ ->

        }
        alert.show()
    }

    private fun openFromDatePickerDialog() {
        val dialog = DatePickerDialog(
            requireContext(),
            { view, year, month, day ->
                viewModel.querySearch(filters.apply {
                    from = "%04d/%02d/%02d".format(year, month + 1, day)
                })
            },
            Constants.DEFAULT_FROM_DATE.split("/")[0].toInt(),
            Constants.DEFAULT_FROM_DATE.split("/")[1].toInt(),
            Constants.DEFAULT_FROM_DATE.split("/")[2].toInt()
        )
        dialog.show()
    }

    private fun openToDatePickerDialog() {
        val dialog = DatePickerDialog(
            requireContext(),
            { view, year, month, day ->
                viewModel.querySearch(filters.apply {
                    to = "%04d/%02d/%02d".format(year, month + 1, day)
                })
            },
            NewsUtility.getDate(System.currentTimeMillis()).split("/")[0].toInt(),
            NewsUtility.getDate(System.currentTimeMillis()).split("/")[1].toInt(),
            NewsUtility.getDate(System.currentTimeMillis()).split("/")[2].toInt()
        )
        dialog.show()
    }
}