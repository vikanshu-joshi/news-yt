package com.vikanshu.newsyt.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vikanshu.newsyt.Constants
import com.vikanshu.newsyt.ui.fragment.ArticleFragment

class NewsViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(
        fragmentManager,
        lifecycle
    ) {
    override fun getItemCount() = Constants.CATEGORIES.size

    override fun createFragment(position: Int): Fragment {
        val tabs = Constants.CATEGORIES.keys.toList()
        return ArticleFragment(tabName = tabs[position])
    }
}