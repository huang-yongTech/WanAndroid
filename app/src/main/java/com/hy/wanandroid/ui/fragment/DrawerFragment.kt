package com.hy.wanandroid.ui.fragment

import com.hy.wanandroid.ui.viewmodel.DrawerViewModel
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.data.bean.MenuItem
import com.hy.wanandroid.ui.adapter.MenuItemAdapter
import com.hy.wanandroid.ui.databinding.FragmentDrawerBinding
import java.util.ArrayList

/**
 * 侧滑菜单fragment
 */
class DrawerFragment : Fragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mBinding: FragmentDrawerBinding? = null
    private var mViewModel: DrawerViewModel? = null
    private var mMenuItemList: MutableList<MenuItem?>? = null
    private var mAdapter: MenuItemAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParam1 = arguments?.getString(ARG_PARAM1)
        mParam2 = arguments?.getString(ARG_PARAM2)
        mViewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
            .get(DrawerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drawer, container, false)
        mBinding = FragmentDrawerBinding.bind(view)
        mBinding?.vm = mViewModel
        initRecyclerView()
        initData()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {
        mAdapter = MenuItemAdapter()
        mBinding?.drawerRecyclerView?.adapter = mAdapter
        mAdapter?.setOnItemClickListener { adapter, view, position -> }
    }

    private fun initData() {
        mMenuItemList = ArrayList()
        mMenuItemList?.add(MenuItem(R.drawable.ic_unfavor, "我的收藏"))
        mMenuItemList?.add(MenuItem(R.drawable.ic_knowledge_system, "知识体系"))
        mMenuItemList?.add(MenuItem(R.drawable.ic_classification, "项目分类"))
        mMenuItemList?.add(MenuItem(R.drawable.ic_navigation, "网址导航"))
        mMenuItemList?.add(MenuItem(R.drawable.ic_about, "关于我们"))
        mAdapter?.setNewData(mMenuItemList)
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(param1: String?, param2: String?): DrawerFragment {
            val fragment = DrawerFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}