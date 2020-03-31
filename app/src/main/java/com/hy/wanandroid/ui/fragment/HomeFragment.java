package com.hy.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hy.wanandroid.library.base.BaseFragment;
import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.data.bean.JsonRootBean;
import com.hy.wanandroid.data.bean.PageData;
import com.hy.wanandroid.library.widget.LinearItemDecoration;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.adapter.ArticleListAdapter;
import com.hy.wanandroid.ui.databinding.FragmentHomeBinding;
import com.hy.wanandroid.ui.viewmodel.HomeViewModel;

import java.util.Objects;

/**
 * 首页fragment
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mBinding;
    private ArticleListAdapter mAdapter;

    public HomeFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mHomeViewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mBinding = FragmentHomeBinding.bind(view);
        mBinding.setClickProxy(new ClickProxy());
        mBinding.setVm(mHomeViewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new ArticleListAdapter();
        mBinding.homeRecyclerView.addItemDecoration(new LinearItemDecoration(Objects.requireNonNull(getContext()), LinearItemDecoration.VERTICAL));
        mBinding.homeRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

            }
        });

        mHomeViewModel.queryHomeArticleList(0).observe(getViewLifecycleOwner(), new Observer<JsonRootBean<PageData<Article>>>() {
            @Override
            public void onChanged(JsonRootBean<PageData<Article>> pageDataJsonRootBean) {
                if (pageDataJsonRootBean != null) {
                    mAdapter.setNewData(pageDataJsonRootBean.getData().getDatas());
                }
            }
        });
    }

    public static class ClickProxy {
        public void clickEvent(View view) {
            switch (view.getId()) {
                case R.id.public_menu_btn:
                    Toast.makeText(view.getContext(), "菜单点击事件", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.public_search_btn:
                    Navigation.findNavController(view).navigate(R.id.action_home_fragment_to_search_fragment);
                    break;
            }
        }
    }
}
