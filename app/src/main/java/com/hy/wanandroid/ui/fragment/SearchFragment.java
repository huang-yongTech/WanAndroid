package com.hy.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hy.wanandroid.data.bean.HotWord;
import com.hy.wanandroid.data.bean.JsonListRootBean;
import com.hy.wanandroid.library.base.BaseFragment;
import com.hy.wanandroid.library.util.BarUtils;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.adapter.SearchKeyAdapter;
import com.hy.wanandroid.ui.databinding.FragmentSearchBinding;
import com.hy.wanandroid.ui.viewmodel.SearchViewModel;

import java.util.List;

/**
 * 查找界面fragment
 */
public class SearchFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private SearchViewModel mSearchViewModel;
    private FragmentSearchBinding mBinding;

    private SearchKeyAdapter mHistoryAdapter;
    private SearchKeyAdapter mHotKeyAdapter;

    public SearchFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

        mSearchViewModel = new ViewModelProvider(this, getDefaultViewModelProviderFactory()).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mBinding = FragmentSearchBinding.bind(view);
        mBinding.setVm(mSearchViewModel);
        BarUtils.setAppToolBarMarginTop(getContext(), mBinding.searchToolbar);

        initView();
        initAdapter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();
    }

    private void initView() {
        mBinding.searchKeyWordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBinding.searchKeyWordEt.setSelected(!TextUtils.isEmpty(s));
                mSearchViewModel.mSearchEnabled.set(!TextUtils.isEmpty(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        clickRightClear(mBinding.searchKeyWordEt);
    }

    private void initAdapter() {
        mHistoryAdapter = new SearchKeyAdapter();
        mBinding.searchHistoryRecyclerView.setAdapter(mHistoryAdapter);
        mHistoryAdapter.setNewData(mSearchViewModel.mHistoryKeyList.get());

        mHotKeyAdapter = new SearchKeyAdapter();
        mBinding.searchHotWordRecyclerView.setAdapter(mHotKeyAdapter);

        mHistoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                String name = mHistoryAdapter.getData().get(position).getName();
                mSearchViewModel.mSearchKey.set(name);
            }
        });
        mHotKeyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                String name = mHotKeyAdapter.getData().get(position).getName();
                mSearchViewModel.mSearchKey.set(name);
            }
        });
    }

    @Override
    protected void getData() {
        mSearchViewModel.getHistoryKey().observe(getViewLifecycleOwner(), new Observer<List<HotWord>>() {
            @Override
            public void onChanged(List<HotWord> hotWords) {
                mHistoryAdapter.setNewData(hotWords);
            }
        });

        mSearchViewModel.getHotWords().observe(getViewLifecycleOwner(), new Observer<JsonListRootBean<HotWord>>() {
            @Override
            public void onChanged(JsonListRootBean<HotWord> hotWordJsonListRootBean) {
                mHotKeyAdapter.setNewData(hotWordJsonListRootBean.getData());
            }
        });
    }
}
