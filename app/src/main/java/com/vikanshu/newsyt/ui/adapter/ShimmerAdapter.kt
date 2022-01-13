package com.vikanshu.newsyt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vikanshu.newsyt.R
import com.vikanshu.newsyt.databinding.RvShimmerBinding

class ShimmerAdapter : RecyclerView.Adapter<ShimmerAdapter.ViewHolder>() {

    class ViewHolder(val binding: RvShimmerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_shimmer,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = Unit

    override fun getItemCount() = 10

}