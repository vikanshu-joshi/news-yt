package com.vikanshu.newsyt.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikanshu.newsyt.R
import com.vikanshu.newsyt.databinding.FragmentSearchBinding
import com.vikanshu.newsyt.model.Filters
import com.vikanshu.newsyt.ui.adapter.ArticlesAdapter
import com.vikanshu.newsyt.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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

            }
        }
    }
}