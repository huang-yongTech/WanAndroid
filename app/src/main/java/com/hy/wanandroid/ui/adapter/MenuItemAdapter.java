package com.hy.wanandroid.ui.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hy.wanandroid.data.bean.MenuItem;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.databinding.ItemMenuLayoutBinding;

/**
 * author：created by huangyong on 2020/4/7 11:55
 * email：756655135@qq.com
 * description :
 */
public class MenuItemAdapter extends BaseQuickAdapter<MenuItem, BaseViewHolder> {
    public MenuItemAdapter() {
        super(R.layout.item_menu_layout);
    }

    @Override
    protected void onItemViewHolderCreated(@NonNull BaseViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, MenuItem menuItem) {
        if (menuItem == null) {
            return;
        }

        ItemMenuLayoutBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setMenu(menuItem);
            binding.executePendingBindings();
        }
    }
}
