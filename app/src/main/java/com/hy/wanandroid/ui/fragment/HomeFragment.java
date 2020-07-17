package com.hy.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.hy.wanandroid.library.base.BaseFragment;
import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.data.bean.JsonRootBean;
import com.hy.wanandroid.data.bean.PageData;
import com.hy.wanandroid.library.widget.LinearItemDecoration;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.adapter.ArticleListAdapter;
import com.hy.wanandroid.ui.databinding.FragmentHomeBinding;
import com.hy.wanandroid.ui.viewmodel.HomeViewModel;
import com.hy.wanandroid.library.widget.CustomLoadMoreView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
        mBinding.setVm(mHomeViewModel);
        mBinding.setLifecycleOwner(this);

        //通过Choreographer来监控卡顿，主要是通过在doFrame方法中统计该方法前后两次执行时间间隔是否超过16.6ms，
        //进而来判断是否发生丢帧现象，如果发生丢帧，则可能会出现卡顿现象
//        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
//            long lastFrameTimeNanos = 0L;
//
//            @Override
//            public void doFrame(long frameTimeNanos) {
//                Log.i(TAG, "----doFrame----");
//
//                if (lastFrameTimeNanos != 0L) {
//                    long diffTimeNanos = TimeUnit.MILLISECONDS.convert(frameTimeNanos - lastFrameTimeNanos,
//                            TimeUnit.NANOSECONDS);
//                    if (diffTimeNanos > 16.6f) {
//                        Log.i(TAG, "doFrame: 发生丢帧");
//                    }
//                }
//                lastFrameTimeNanos = frameTimeNanos;
//
//                Choreographer.getInstance().postFrameCallback(this);
//            }
//        });

        initDrawer();
        initRefresh();
        initAdapter();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();
    }

    private void initDrawer() {
        mHomeViewModel.mOpenDrawer.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    mBinding.homeDrawer.openDrawer(GravityCompat.START);
                } else {
                    mBinding.homeDrawer.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void initAdapter() {
        mAdapter = new ArticleListAdapter();

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

            }
        });

        Objects.requireNonNull(mAdapter.getLoadMoreModule()).setLoadMoreView(new CustomLoadMoreView());
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        mAdapter.getLoadMoreModule().setAutoLoadMore(true);
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        mAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
    }

    private void initRecyclerView() {
        mBinding.homeRecyclerView.addItemDecoration(new LinearItemDecoration(requireContext(), LinearItemDecoration.VERTICAL));
        mBinding.homeRecyclerView.setAdapter(mAdapter);
    }

    private void initRefresh() {
        mBinding.homeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mBinding.homeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 1200);
                mBinding.homeRefreshLayout.finishRefresh(1500);
            }
        });
    }

    /**
     * 下拉刷新数据
     */
    @Override
    protected void getData() {
        mAdapter.setEmptyView(getLoadingView(mBinding.homeRecyclerView));

        mHomeViewModel.queryHomeArticleList(0).observe(getViewLifecycleOwner(), new Observer<JsonRootBean<PageData<Article>>>() {
            @Override
            public void onChanged(JsonRootBean<PageData<Article>> pageDataJsonRootBean) {
                if (pageDataJsonRootBean != null) {
                    mAdapter.setNewData(pageDataJsonRootBean.getData().getDatas());
                } else {
                    mAdapter.setEmptyView(getEmptyDataView(mBinding.homeRecyclerView));
                }
            }
        });
    }

    /**
     * 上拉加载数据
     */
    private void loadMore() {

    }
}
