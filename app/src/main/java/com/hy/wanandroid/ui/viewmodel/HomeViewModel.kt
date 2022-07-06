package com.hy.wanandroid.ui.viewmodel

import android.view.View
import com.hy.wanandroid.ui.R
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hy.wanandroid.data.api.RetrofitUtils
import com.hy.wanandroid.data.api.HomeApi
import androidx.navigation.Navigation
import com.hy.wanandroid.data.bean.JsonRootBean
import com.hy.wanandroid.data.state.BaseViewModel
import com.hy.wanandroid.data.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * author：created by huangyong on 2020/3/26 11:23
 * email：756655135@qq.com
 * description :
 */
class HomeViewModel : BaseViewModel() {
    //侧滑菜单打开标志
    val mOpenDrawer = MutableLiveData<Boolean>()

    private val _mHomeArticleState = MutableStateFlow<UiState>(UiState.Success(null))

    val mHomeArticleState: StateFlow<UiState> = _mHomeArticleState

    var mHomeArticleData = MutableLiveData<JsonRootBean<Any?>?>(null)

//    suspend fun queryHomeArticleList(page: Int): JsonRootBean<Any?>? {
//        return withContext(Dispatchers.IO) {
//            RetrofitUtils.instance
//                .getApiService(HomeApi::class.java)
//                .queryHomeArticleList(page)
//        }
//    }

    val mArticleData = MutableLiveData<UiState>(UiState.Success(null))

    fun queryHomeArticleList(page: Int, isLoadMore: Boolean) {
        this.isLoadMore = isLoadMore
        viewModelScope.launch {
            getData(page)
                .flowOn(Dispatchers.IO)
                .catch { exception ->
                    _mHomeArticleState.value = UiState.Error(handleObjError(exception))
                }
                .collect {
                    _mHomeArticleState.value = UiState.Success(it)
                }
        }
    }

    private fun getData(page: Int) = flow {
        val articleData = RetrofitUtils.instance
            .getApiService(HomeApi::class.java)
            .queryHomeArticleList(page)
        emit(articleData)
    }

//    fun queryHomeArticleList(page: Int) {
//        getData(page).runCatching {
//            mHomeArticleData.value = this.value
//        }.onFailure {
//            val jsonRootBean = JsonRootBean<Any?>()
//            jsonRootBean.errorMsg = handleStrError(it)
//            jsonRootBean.errorCode = -100
//            mHomeArticleData.value = jsonRootBean
//        }
//        viewModelScope.launch {
//            mHomeArticleData.value = getData(page)
//        }
//    }

//    private suspend fun getData(page: Int): JsonRootBean<Any?>? {
//        return try {
//            RetrofitUtils.instance
//                .getApiService(HomeApi::class.java)
//                .queryHomeArticleList(page)
//        } catch (e: Throwable) {
//            val jsonRootBean = JsonRootBean<Any?>()
//            jsonRootBean.errorMsg = handleStrError(e)
//            jsonRootBean.errorCode = -100
//            jsonRootBean
//        }
//    }

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