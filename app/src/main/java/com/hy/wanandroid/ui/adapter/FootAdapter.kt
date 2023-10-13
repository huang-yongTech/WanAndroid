package com.hy.wanandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.LoadStates
import androidx.paging.LoadType
import androidx.recyclerview.widget.RecyclerView
import com.hy.wanandroid.ui.databinding.ItemPageFootBinding

class FootAdapter(val context: Context) : LoadStateAdapter<FootAdapter.FootViewHolder>() {

    class FootViewHolder(var binding: ItemPageFootBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: FootViewHolder, loadState: LoadState) {
        when (loadState) {
            is LoadState.NotLoading -> {
                holder.binding.itemPageFootLoadingLl.visibility = View.GONE
            }

            is LoadState.Loading -> {
                holder.binding.itemPageFootLoadingLl.visibility = View.VISIBLE
            }

            else -> {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FootViewHolder {
        return FootViewHolder(
            ItemPageFootBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getStateViewType(loadState: LoadState): Int {
        return LoadType.APPEND.ordinal
    }

    /**
     * 重写该方法，否则底部LoadStatus不展示，闪过
     * By default, both [LoadState.Loading] and [LoadState.Error] are presented as adapter items,
     * other states are not. To configure this, override [displayLoadStateAsItem].
     */
    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading ||
                loadState is LoadState.Error ||
                (loadState is LoadState.NotLoading && loadState.endOfPaginationReached);
    }
}