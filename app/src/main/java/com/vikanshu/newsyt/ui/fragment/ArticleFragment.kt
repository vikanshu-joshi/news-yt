package com.vikanshu.newsyt.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikanshu.newsyt.R
import com.vikanshu.newsyt.databinding.FragmentArticleBinding
import com.vikanshu.newsyt.db.Article
import com.vikanshu.newsyt.ui.adapter.ArticlesAdapter
import com.vikanshu.newsyt.ui.adapter.ShimmerAdapter
import com.vikanshu.newsyt.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ArticleFragment constructor(val tabName: String) : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private val viewModel: NewsViewModel by activityViewModels()
    private lateinit var adapter: ArticlesAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater)
        adapter = ArticlesAdapter(
            requireContext(),
            android.R.drawable.btn_star,
            onLoadMore = {
                viewModel.increasePageForCategory(tabName)
            },
            onIconClicked = { it, position ->
                viewModel.saveArticleInDB(
                    Article(
                        it.source.name,
                        it.title,
                        it.description,
                        it.url,
                        it.image,
                        it.publishedAt,
                        it.content
                    )
                )
                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_LONG).show()
            }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("$tabName : onViewCreated")
        binding.rvArticles.apply {
            adapter = this@ArticleFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.srlArticles.setOnRefreshListener {
            binding.srlArticles.isRefreshing = true
            viewModel.refreshData(tabName)
        }
        viewModel.getHeadLines(tabName).observe(this, {
            if (binding.srlArticles.isRefreshing) {
                binding.srlArticles.isRefreshing = false
            }
            adapter.submitList(it)
        })
    }
}