package com.vikanshu.newsyt.utility

import androidx.recyclerview.widget.DiffUtil
import com.vikanshu.newsyt.model.Article

class ArticleDiffUtil (
    val oldList: List<Article>,
    val newList: List<Article>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].image == newList[newItemPosition].image
}