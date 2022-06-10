package com.hy.wanandroid.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.hy.wanandroid.data.bean.HotWord
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hy.wanandroid.ui.R
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hy.wanandroid.library.util.Constant
import com.hy.wanandroid.library.util.RandomUtils
import com.hy.wanandroid.ui.databinding.ItemWordLayoutBinding

/**
 * author：created by huangyong on 2020/3/26 19:06
 * email：756655135@qq.com
 * description : 搜索界面列表adapter
 */
class SearchKeyAdapter : BaseQuickAdapter<HotWord?, BaseViewHolder>(R.layout.item_word_layout) {
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: HotWord?) {
        val binding: ItemWordLayoutBinding? = helper.getBinding()
        if (binding != null) {
            binding.word = item
            binding.itemWordTv.setTextColor(
                RandomUtils.INSTANCE.randomColor(
                    Constant.COLOR_RGB_MIN,
                    Constant.COLOR_RGB_MAX
                )
            )
            binding.executePendingBindings()
        }
    }
}