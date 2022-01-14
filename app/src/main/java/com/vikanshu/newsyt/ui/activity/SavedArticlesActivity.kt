package com.vikanshu.newsyt.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikanshu.newsyt.R
import com.vikanshu.newsyt.databinding.ActivitySavedArticlesBinding
import com.vikanshu.newsyt.db.Article
import com.vikanshu.newsyt.model.Source
import com.vikanshu.newsyt.ui.adapter.ArticlesAdapter
import com.vikanshu.newsyt.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedArticlesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedArticlesBinding
    private lateinit var adapter: ArticlesAdapter
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Saved Articles"
        adapter = ArticlesAdapter(
            this,
            {},
            onSave = { it, position ->
                viewModel.deleteArticleFromDB(
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
                adapter.removeItem(position)
                Toast.makeText(this, "Removed", Toast.LENGTH_LONG).show()
            }
        )
        binding.rvSaved.apply {
            adapter = this@SavedArticlesActivity.adapter
            layoutManager = LinearLayoutManager(this@SavedArticlesActivity)
        }
        viewModel.getSavedArticles().observe(this, {
            adapter.submitList(mutableListOf<com.vikanshu.newsyt.model.Article>().apply {
                addAll(
                    it.map { article ->
                        com.vikanshu.newsyt.model.Article(
                            article.title,
                            article.description,
                            article.content,
                            article.url,
                            article.urlToImage,
                            article.publishedAt,
                            Source(article.source, "")
                        )
                    }
                )
            })
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.saved.postValue(mutableListOf())
    }
}