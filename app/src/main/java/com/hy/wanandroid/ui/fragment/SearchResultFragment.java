package com.hy.wanandroid.ui.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hy.wanandroid.library.base.BaseFragment;
import com.hy.wanandroid.library.util.BarUtils;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.adapter.ArticleListAdapter;
import com.hy.wanandroid.ui.click.PublicClickProxy;
import com.hy.wanandroid.ui.databinding.FragmentSearchResultBinding;
import com.hy.wanandroid.ui.viewmodel.SearchResultViewModel;

/**
 * 搜索结果界面
 */
public class SearchResultFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private SearchResultViewModel mViewModel;
    private FragmentSearchResultBinding mBinding;

    public SearchResultFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static SearchResultFragment newInstance(String param1, String param2) {
        SearchResultFragment fragment = new SearchResultFragment();
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
        mViewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(SearchResultViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        mBinding = FragmentSearchResultBinding.bind(view);
        mBinding.setVm(mViewModel);
        mBinding.searchResultInclude.setClickProxy(new PublicClickProxy());

        BarUtils.setAppToolBarMarginTop(getContext(), mBinding.searchResultInclude.publicToolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArticleListAdapter adapter = new ArticleListAdapter();
        mBinding.searchResultRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

            }
        });
    }
}
