package com.hy.wanandroid.ui.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.databinding.ItemArticleLayoutBinding;

/**
 * author：created by huangyong on 2020/3/26 14:58
 * email：756655135@qq.com
 * description :
 */
public class ArticleListAdapter extends BaseQuickAdapter<Article, BaseViewHolder> implements LoadMoreModule {
    private static final String TAG = "HomeArticleAdapter";

    public ArticleListAdapter() {
        super(R.layout.item_article_layout);
    }

    @Override
    protected void onItemViewHolderCreated(@NonNull BaseViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);

        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, Article article) {
        if (article == null) {
            return;
        }

        ItemArticleLayoutBinding binding = baseViewHolder.getBinding();
        if (binding != null) {
            binding.setArticle(article);
            binding.executePendingBindings();
        }
    }
}
