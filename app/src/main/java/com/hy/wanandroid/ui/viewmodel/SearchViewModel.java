package com.hy.wanandroid.ui.viewmodel;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import com.hy.wanandroid.data.api.RetrofitUtils;
import com.hy.wanandroid.data.api.SearchApi;
import com.hy.wanandroid.data.bean.HotWord;
import com.hy.wanandroid.data.bean.JsonListRootBean;
import com.hy.wanandroid.data.dao.AppDatabase;
import com.hy.wanandroid.data.dao.SearchHistoryDataSource;
import com.hy.wanandroid.library.util.Constant;
import com.hy.wanandroid.library.util.KeyboardUtils;
import com.hy.wanandroid.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author：created by huangyong on 2020/3/26 18:32
 * email：756655135@qq.com
 * description :
 */
public class SearchViewModel extends AndroidViewModel {
    private static final String TAG = "SearchViewModel";

    //搜索历史
    public final ObservableField<List<HotWord>> mHistoryKeyList = new ObservableField<>(new ArrayList<>());

    //搜索按钮是否可用
    public final ObservableBoolean mSearchEnabled = new ObservableBoolean();

    //搜索框绑定
    public final ObservableField<String> mSearchKey = new ObservableField<>();

    private SearchHistoryDataSource mHistoryDataSource;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application.getBaseContext());
        mHistoryDataSource = new SearchHistoryDataSource(database.historyDao());
    }

    public void insertSearchKey(HotWord hotWord) {
        mHistoryDataSource.insertSearchKey(hotWord);
    }

    public LiveData<List<HotWord>> getHistoryKey() {
        return mHistoryDataSource.getHistoryKey();
    }

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
                mHistoryKeyList.get().clear();
                break;
            case R.id.search_btn:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.TYPE_SEARCH_KEY, mSearchKey.get());
                KeyboardUtils.hideSoftInput(view);
                HotWord hotWord = new HotWord(mSearchKey.get());
                insertSearchKey(hotWord);
                mHistoryKeyList.get().add(hotWord);
                Navigation.findNavController(view).navigate(R.id.action_search_fragment_to_search_result_fragment, bundle);
                break;
        }
    }
}
