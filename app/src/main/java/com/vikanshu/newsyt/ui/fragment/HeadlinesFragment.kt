package com.vikanshu.newsyt.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vikanshu.newsyt.Constants
import com.vikanshu.newsyt.R
import com.vikanshu.newsyt.databinding.FragmentHeadlinesBinding
import com.vikanshu.newsyt.ui.adapter.NewsViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentHeadlinesBinding
    private lateinit var adapter: NewsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesBinding.inflate(layoutInflater)
        adapter = NewsViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pagerHeadlines.adapter = adapter
        TabLayoutMediator(binding.tabHeadlines, binding.pagerHeadlines) { tab, position ->
            tab.text = Constants.CATEGORIES.keys.toList()[position]
        }.attach()
    }
}