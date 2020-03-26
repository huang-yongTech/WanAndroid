package com.hy.wanandroid.ui.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hy.wanandroid.data.bean.HotWord;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.databinding.ItemWordLayoutBinding;

/**
 * author：created by huangyong on 2020/3/26 19:06
 * email：756655135@qq.com
 * description : 搜索界面列表adapter
 */
public class SearchKeyAdapter extends BaseQuickAdapter<HotWord, BaseViewHolder> {
    public SearchKeyAdapter() {
        super(R.layout.item_word_layout);
    }

    @Override
    protected void onItemViewHolderCreated(@NonNull BaseViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);

        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, HotWord hotWord) {
        ItemWordLayoutBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setWord(hotWord);
            binding.executePendingBindings();
        }
    }
}
