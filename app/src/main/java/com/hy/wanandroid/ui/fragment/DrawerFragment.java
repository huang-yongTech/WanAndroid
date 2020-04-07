package com.hy.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hy.wanandroid.data.bean.MenuItem;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.adapter.MenuItemAdapter;
import com.hy.wanandroid.ui.databinding.FragmentDrawerBinding;
import com.hy.wanandroid.ui.viewmodel.DrawerViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧滑菜单fragment
 */
public class DrawerFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FragmentDrawerBinding mBinding;
    private DrawerViewModel mViewModel;
    private List<MenuItem> mMenuItemList;
    private MenuItemAdapter mAdapter;

    public DrawerFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static DrawerFragment newInstance(String param1, String param2) {
        DrawerFragment fragment = new DrawerFragment();
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

        mViewModel = new ViewModelProvider(getViewModelStore(), getDefaultViewModelProviderFactory())
                .get(DrawerViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        mBinding = FragmentDrawerBinding.bind(view);
        mBinding.setVm(mViewModel);

        initRecyclerView();
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecyclerView() {
        mAdapter = new MenuItemAdapter();
        mBinding.drawerRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

            }
        });
    }

    private void initData() {
        mMenuItemList = new ArrayList<>();

        mMenuItemList.add(new MenuItem(R.drawable.ic_unfavor, "我的收藏"));
        mMenuItemList.add(new MenuItem(R.drawable.ic_knowledge_system, "知识体系"));
        mMenuItemList.add(new MenuItem(R.drawable.ic_classification, "项目分类"));
        mMenuItemList.add(new MenuItem(R.drawable.ic_navigation, "网址导航"));
        mMenuItemList.add(new MenuItem(R.drawable.ic_about, "关于我们"));

        mAdapter.setNewData(mMenuItemList);
    }
}
