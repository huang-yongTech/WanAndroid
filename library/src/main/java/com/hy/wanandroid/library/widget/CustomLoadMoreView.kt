package com.hy.wanandroid.library.widget

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hy.wanandroid.library.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View

/**
 * author：created by huangyong on 2020/4/2 10:27
 * email：756655135@qq.com
 * description :
 */
class CustomLoadMoreView : BaseLoadMoreView() {
    override fun getLoadComplete(holder: BaseViewHolder): View {
        return holder.itemView.findViewById(R.id.up_load_more_load_complete_view)
    }

    override fun getLoadEndView(holder: BaseViewHolder): View {
        return holder.itemView.findViewById(R.id.up_load_more_load_end_view)
    }

    override fun getLoadFailView(holder: BaseViewHolder): View {
        return holder.itemView.findViewById(R.id.up_load_more_fail_view)
    }

    override fun getLoadingView(holder: BaseViewHolder): View {
        return holder.itemView.findViewById(R.id.up_load_more_loading_view)
    }

    override fun getRootView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(
            R.layout.view_pull_up_load_more,
            parent, false
        )
    }
}