package com.hy.wanandroid.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.hy.wanandroid.data.bean.Article
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hy.wanandroid.ui.R
import com.chad.library.adapter.base.module.LoadMoreModule
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hy.wanandroid.ui.databinding.ItemArticleLayoutBinding

/**
 * author：created by huangyong on 2020/3/26 14:58
 * email：756655135@qq.com
 * description :
 */
class ArticleListAdapter : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article_layout),
    LoadMoreModule {
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: Article) {
        val binding: ItemArticleLayoutBinding? = helper.getBinding()
        binding?.article = item
        binding?.executePendingBindings()
    }

    companion object {
        private const val TAG = "HomeArticleAdapter"
    }
}