package com.hy.wanandroid.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.data.bean.JsonRootBean;
import com.hy.wanandroid.data.bean.PageData;
import com.hy.wanandroid.library.base.BaseFragment;
import com.hy.wanandroid.library.util.BarUtils;
import com.hy.wanandroid.library.util.Constant;
import com.hy.wanandroid.library.widget.CustomLoadMoreView;
import com.hy.wanandroid.library.widget.LinearItemDecoration;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.adapter.ArticleListAdapter;
import com.hy.wanandroid.ui.click.PublicClickProxy;
import com.hy.wanandroid.ui.databinding.FragmentSearchResultBinding;
import com.hy.wanandroid.ui.viewmodel.SearchResultViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Objects;

/**
 * 搜索结果界面
 */
public class SearchResultFragment extends BaseFragment {
    private static final String TAG = "SearchResultFragment";

    private String mSearchKey;

    private SearchResultViewModel mViewModel;
    private FragmentSearchResultBinding mBinding;
    private ArticleListAdapter mAdapter;

    public SearchResultFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static SearchResultFragment newInstance(String searchKey) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString(Constant.TYPE_SEARCH_KEY, searchKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchKey = getArguments().getString(Constant.TYPE_SEARCH_KEY);
        }
        mViewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(SearchResultViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        mBinding = FragmentSearchResultBinding.bind(view);
        mBinding.setVm(mViewModel);
        mBinding.searchResultInclude.setClickProxy(new PublicClickProxy());
        mBinding.searchResultInclude.setTitle(mSearchKey);
        BarUtils.setAppToolBarMarginTop(getContext(), mBinding.searchResultInclude.publicToolbar);

        initRefreshLayout();
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();
    }

    private void initRefreshLayout() {
        mBinding.searchResultRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mBinding.searchResultRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 1200);
                mBinding.searchResultRefreshLayout.finishRefresh(1500);
            }
        });
    }

    private void initRecyclerView() {
        mBinding.searchResultRecyclerView.addItemDecoration(
                new LinearItemDecoration(Objects.requireNonNull(getContext()), LinearItemDecoration.VERTICAL));
        mAdapter = new ArticleListAdapter();
        mBinding.searchResultRecyclerView.setAdapter(mAdapter);
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

    /**
     * 加载更多
     */
    private void loadMore() {

    }

    @Override
    protected void getData() {
        mAdapter.setEmptyView(getLoadingView(mBinding.searchResultRecyclerView));

        mViewModel.queryArticlesByKey(0, mSearchKey).observe(getViewLifecycleOwner(), new Observer<JsonRootBean<PageData<Article>>>() {
            @Override
            public void onChanged(JsonRootBean<PageData<Article>> pageDataJsonRootBean) {
                if (pageDataJsonRootBean != null) {
                    mAdapter.setNewData(pageDataJsonRootBean.getData().getDatas());
                }
            }
        });
    }
}
