package com.hy.wanandroid.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hy.wanandroid.data.bean.Article
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hy.wanandroid.library.util.ToastUtils
import com.hy.wanandroid.ui.databinding.ItemArticleLayoutBinding

/**
 * author：created by huangyong on 2020/3/26 14:58
 * email：756655135@qq.com
 * description :
 */
class ArticleAdapter(
    private val context: Context,
    val itemClick: (Int, Article?, ArticleAdapter) -> Unit
) :
    PagingDataAdapter<Article, ArticleAdapter.ViewHolder>(COMPARATOR) {


    class ViewHolder(val binding: ItemArticleLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article?) {
            binding.article = article
            binding.executePendingBindings()
        }
    }

    companion object {
        private const val TAG = "ArticleAdapter"

        private val COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArticleLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
        //item点击事件
        holder.binding.root.setOnClickListener {
            itemClick(position, article, this)
        }
        holder.binding.itemArticleFavorIv.setOnCheckedChangeListener { buttonView, isChecked ->
            ToastUtils.showToast("位置${position}的状态为${isChecked}")
        }
    }
}