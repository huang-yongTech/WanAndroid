package com.hy.wanandroid.ui.viewmodel

import android.app.Application
import android.os.Bundle
import android.view.View
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.data.api.RetrofitUtils
import androidx.navigation.Navigation
import androidx.databinding.ObservableField
import com.hy.wanandroid.data.api.SearchApi
import androidx.lifecycle.AndroidViewModel
import com.hy.wanandroid.data.bean.HotWord
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hy.wanandroid.data.dao.SearchHistoryDataSource
import com.hy.wanandroid.data.bean.JsonListRootBean
import com.hy.wanandroid.library.util.Constant
import com.hy.wanandroid.library.util.KeyboardUtils
import java.util.ArrayList

/**
 * author：created by huangyong on 2020/3/26 18:32
 * email：756655135@qq.com
 * description :
 */
class SearchViewModel(application: Application) : AndroidViewModel(application) {
    //搜索历史
    val mHistoryKeyList = ObservableField<MutableList<HotWord?>?>(ArrayList())

    //搜索按钮是否可用
    @JvmField
    val mSearchEnabled = ObservableBoolean()

    //搜索框绑定
    @JvmField
    val mSearchKey = ObservableField<String>()
    private val mHistoryDataSource: SearchHistoryDataSource = SearchHistoryDataSource(application)
    private fun insertSearchKey(hotWord: HotWord) {
        mHistoryDataSource.insertSearchKey(hotWord)
    }

    val historyKey: LiveData<MutableList<HotWord?>?>?
        get() = mHistoryDataSource.historyKey

    /**
     * 获取热搜词
     *
     * @return 结果集
     */
    val hotWords: LiveData<JsonListRootBean<HotWord?>?>?
        get() = RetrofitUtils.instance
            .getApiService(SearchApi::class.java)
            .hotWords

    /**
     * 点击事件
     *
     * @param view view
     */
    fun clickEvent(view: View) {
        when (view.id) {
            R.id.search_back_btn -> Navigation.findNavController(view).navigateUp()
            R.id.search_delete_history_btn -> mHistoryDataSource.deleteKeys()
            R.id.search_btn -> {
                val bundle = Bundle()
                bundle.putString(Constant.TYPE_SEARCH_KEY, mSearchKey.get())
                KeyboardUtils.hideSoftInput(view)
                val hotWord = HotWord(mSearchKey.get()!!)
                insertSearchKey(hotWord)
                Navigation.findNavController(view)
                    .navigate(R.id.action_search_fragment_to_search_result_fragment, bundle)
            }
        }
    }

    companion object {
        private const val TAG = "SearchViewModel"
    }

}