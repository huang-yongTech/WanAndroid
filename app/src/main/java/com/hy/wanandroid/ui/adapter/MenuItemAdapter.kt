package com.hy.wanandroid.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hy.wanandroid.ui.R
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hy.wanandroid.data.bean.MenuItem
import com.hy.wanandroid.ui.databinding.ItemMenuLayoutBinding

/**
 * author：created by huangyong on 2020/4/7 11:55
 * email：756655135@qq.com
 * description :
 */
class MenuItemAdapter : BaseQuickAdapter<MenuItem?, BaseViewHolder>(R.layout.item_menu_layout) {
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: MenuItem?) {
        if (item == null) {
            return
        }
        val binding: ItemMenuLayoutBinding? = helper.getBinding()
        if (binding != null) {
            binding.menu = item
            binding.executePendingBindings()
        }
    }
}