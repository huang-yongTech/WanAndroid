package com.hy.wanandroid.ui.viewmodel

import android.view.View
import com.hy.wanandroid.ui.R
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.hy.wanandroid.data.state.BaseViewModel
import com.hy.wanandroid.ui.source.ArticlePagingSource

/**
 * author：created by huangyong on 2020/3/26 11:23
 * email：756655135@qq.com
 * description :
 */
class HomeViewModel : BaseViewModel() {
    //侧滑菜单打开标志
    val mOpenDrawer = MutableLiveData<Boolean>()

    fun getArticleList() = Pager(PagingConfig(pageSize = 1)) {
        ArticlePagingSource()
    }.flow

    /**
     * 点击事件
     *
     * @param view view
     */
    fun clickEvent(view: View) {
        when (view.id) {
            R.id.home_menu_btn -> mOpenDrawer.setValue(true)
            R.id.home_search_btn -> {
                Navigation.findNavController(view)
                    .navigate(R.id.action_home_fragment_to_search_fragment)
                //在界面跳转的时候，将侧滑栏状态设置为关闭状态，避免在重新返回该界面是侧滑栏因为是打开状态而自动打开
                mOpenDrawer.setValue(false)
            }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
}