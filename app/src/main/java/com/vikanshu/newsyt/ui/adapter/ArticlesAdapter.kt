package com.vikanshu.newsyt.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vikanshu.newsyt.R
import com.vikanshu.newsyt.databinding.RvArticleBinding
import com.vikanshu.newsyt.model.Article
import com.vikanshu.newsyt.utility.ArticleDiffUtil
import com.vikanshu.newsyt.utility.NewsUtility
import com.thefinestartist.finestwebview.FinestWebView
import com.vikanshu.newsyt.ui.viewmodels.NewsViewModel
import timber.log.Timber


class ArticlesAdapter(
    val context: Context,
    val icon: Int?,
    val onLoadMore: () -> Unit,
    val onIconClicked: (Article, Int) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private val articles = mutableListOf<Article>()

    class ViewHolder(val binding: RvArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        if (position == articles.size - 1) onLoadMore() // TODO
        holder.binding.apply {
            article = articles[position]
            Glide.with(context)
                .load(articles[position].image)
                .placeholder(R.drawable.ic_sync)
                .error(R.drawable.ic_sync_problem)
                .into(ivArticle)
            tvDate.text = NewsUtility.formatDate(articles[position].publishedAt)
            root.setOnClickListener {
                FinestWebView.Builder(context).webViewSupportZoom(true).show(articles[position].url)
            }

            if (icon == null) {
                ivStar.visibility = View.GONE
            } else {
                ivStar.setImageDrawable(ContextCompat.getDrawable(context, icon))
                ivStar.setOnClickListener {
                    onIconClicked(articles[position], position)
                }
            }
        }
    }

    override fun getItemCount() = articles.size

    fun removeItem(position: Int) {
        this.articles.removeAt(position)
        this.notifyItemRemoved(position)
    }

    fun submitList(articles: List<Article>) {
        val diffUtil = ArticleDiffUtil(this.articles, articles)
        val results = DiffUtil.calculateDiff(diffUtil)
        this.articles.clear()
        this.articles.addAll(articles)
        results.dispatchUpdatesTo(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        this.articles.clear()
        this.notifyDataSetChanged()
    }
}