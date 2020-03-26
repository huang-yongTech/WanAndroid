package com.hy.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.data.bean.JsonRootBean;
import com.hy.wanandroid.data.bean.PageData;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.adapter.HomeArticleAdapter;
import com.hy.wanandroid.ui.databinding.FragmentHomeBinding;
import com.hy.wanandroid.ui.viewmodel.HomeViewModel;

/**
 * 首页fragment
 */
public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private HomeViewModel mHomeViewModel;
    private FragmentHomeBinding mBinding;
    private HomeArticleAdapter mAdapter;

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
        mBinding.setVm(mHomeViewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new HomeArticleAdapter();

        mHomeViewModel.queryHomeArticleList(0).observe(getViewLifecycleOwner(), new Observer<JsonRootBean<PageData<Article>>>() {
            @Override
            public void onChanged(JsonRootBean<PageData<Article>> pageDataJsonRootBean) {

            }
        });
    }
}
