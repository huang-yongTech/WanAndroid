package com.hy.wanandroid.ui.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.hy.wanandroid.data.api.RetrofitUtils;
import com.hy.wanandroid.data.api.SearchApi;
import com.hy.wanandroid.data.bean.HotWord;
import com.hy.wanandroid.data.bean.JsonListRootBean;
import com.hy.wanandroid.library.util.Constant;
import com.hy.wanandroid.ui.R;

import java.util.List;

/**
 * author：created by huangyong on 2020/3/26 18:32
 * email：756655135@qq.com
 * description :
 */
public class SearchViewModel extends ViewModel {
    private static final String TAG = "SearchViewModel";

    public final ObservableField<String> mSearchKey = new ObservableField<>();

    /**
     * 搜索历史列表
     */
    public final ObservableField<List<String>> mHistoryKeys = new ObservableField<>();

    /**
     * 获取热搜词
     *
     * @return 结果集
     */
    public LiveData<JsonListRootBean<HotWord>> getHotWords() {
        return RetrofitUtils.getInstance()
                .getApiService(SearchApi.class)
                .getHotWords()
                ;
    }

    /**
     * 点击事件
     *
     * @param view view
     */
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.search_back_btn:
                Navigation.findNavController(view).navigateUp();
                break;
            case R.id.search_delete_history_btn:
                if (mHistoryKeys.get() != null) {
                    mHistoryKeys.get().clear();
                }
                break;
            case R.id.search_btn:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.TYPE, mSearchKey.get());
                Log.i(TAG, "SearchFragment传值: " + mSearchKey.get());
                Navigation.findNavController(view).navigate(R.id.nav_fragment_search_result, bundle);
                break;
        }
    }
}
