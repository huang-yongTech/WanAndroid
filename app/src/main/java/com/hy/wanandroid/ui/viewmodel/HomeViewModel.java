package com.hy.wanandroid.ui.viewmodel;

import android.view.View;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.hy.wanandroid.data.api.HomeApi;
import com.hy.wanandroid.data.api.RetrofitUtils;
import com.hy.wanandroid.data.bean.PageData;
import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.data.bean.JsonRootBean;
import com.hy.wanandroid.ui.R;

/**
 * author：created by huangyong on 2020/3/26 11:23
 * email：756655135@qq.com
 * description :
 */
public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";

    //侧滑菜单打开标志
    public final MutableLiveData<Boolean> mOpenDrawer = new MutableLiveData<>();

    public LiveData<JsonRootBean<PageData<Article>>> queryHomeArticleList(int page) {
        return RetrofitUtils.getInstance()
                .getApiService(HomeApi.class)
                .queryHomeArticleList(page)
                ;
    }

    /**
     * 点击事件
     *
     * @param view view
     */
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.public_menu_btn:

                break;
            case R.id.public_search_btn:
                Navigation.findNavController(view).navigate(R.id.action_home_fragment_to_search_fragment);
                break;
        }
    }
}
