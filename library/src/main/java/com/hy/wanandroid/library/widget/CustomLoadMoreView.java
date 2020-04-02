package com.hy.wanandroid.library.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hy.wanandroid.library.R;

import java.util.Objects;

/**
 * author：created by huangyong on 2020/4/2 10:27
 * email：756655135@qq.com
 * description :
 */
public class CustomLoadMoreView extends BaseLoadMoreView {
    @NonNull
    @Override
    public View getLoadComplete(@NonNull BaseViewHolder baseViewHolder) {
        return Objects.requireNonNull(baseViewHolder.findView(R.id.up_load_more_load_complete_view));
    }

    @NonNull
    @Override
    public View getLoadEndView(@NonNull BaseViewHolder baseViewHolder) {
        return Objects.requireNonNull(baseViewHolder.findView(R.id.up_load_more_load_end_view));
    }

    @NonNull
    @Override
    public View getLoadFailView(@NonNull BaseViewHolder baseViewHolder) {
        return Objects.requireNonNull(baseViewHolder.findView(R.id.up_load_more_fail_view));
    }

    @NonNull
    @Override
    public View getLoadingView(@NonNull BaseViewHolder baseViewHolder) {
        return Objects.requireNonNull(baseViewHolder.findView(R.id.up_load_more_loading_view));
    }

    @NonNull
    @Override
    public View getRootView(@NonNull ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_pull_up_load_more,
                viewGroup, false);
    }
}
