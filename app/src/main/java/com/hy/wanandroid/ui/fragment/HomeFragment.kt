package com.hy.wanandroid.ui.fragment

import com.hy.wanandroid.library.base.BaseFragment
import com.hy.wanandroid.ui.viewmodel.HomeViewModel
import com.hy.wanandroid.ui.adapter.ArticleListAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.google.gson.reflect.TypeToken
import com.hy.wanandroid.data.bean.*
import com.hy.wanandroid.data.state.UiState
import com.hy.wanandroid.library.util.GsonUtils
import com.hy.wanandroid.library.util.ToastUtils
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.library.widget.CustomLoadMoreView
import com.hy.wanandroid.library.widget.LinearItemDecoration
import com.hy.wanandroid.ui.databinding.FragmentHomeBinding
import com.hy.wanandroid.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

/**
 * 首页fragment
 */
class HomeFragment : BaseFragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mHomeViewModel: HomeViewModel? = null
    private var mSharedViewModel: SharedViewModel? = null
    private var mBinding: FragmentHomeBinding? = null
    private var mAdapter: ArticleListAdapter? = null
    private var currPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParam1 = arguments?.getString(ARG_PARAM1)
        mParam2 = arguments?.getString(ARG_PARAM2)
        mHomeViewModel =
            ViewModelProvider(this, defaultViewModelProviderFactory)[HomeViewModel::class.java]
        mSharedViewModel = getActivityScopeViewModel(mActivity, SharedViewModel::class.java)

        Log.i(TAG, "onCreate: HomeFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        mBinding = FragmentHomeBinding.bind(view)
        mBinding?.vm = mHomeViewModel
        mBinding?.lifecycleOwner = this

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
        initDrawer()
        initRefresh()
        initAdapter()
        initRecyclerView()
        getData()
        return view
    }

    private fun initDrawer() {
        mHomeViewModel?.mOpenDrawer?.observe(viewLifecycleOwner, Observer { aBoolean ->
            if (aBoolean) {
                mBinding?.homeDrawer?.openDrawer(GravityCompat.START)
            } else {
                mBinding?.homeDrawer?.closeDrawer(GravityCompat.START)
            }
        })
    }

    private fun initAdapter() {
        mAdapter = ArticleListAdapter()
        mAdapter?.setOnItemClickListener { adapter, view, position ->
            ToastUtils.showToast("位置$position")
        }
        mAdapter?.loadMoreModule?.loadMoreView = CustomLoadMoreView()
        mAdapter?.loadMoreModule?.setOnLoadMoreListener { loadMore() }
        mAdapter?.loadMoreModule?.isAutoLoadMore = true
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        mAdapter?.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
    }

    private fun initRecyclerView() {
        mBinding?.homeRecyclerView?.addItemDecoration(
            LinearItemDecoration(
                requireContext(),
                LinearItemDecoration.VERTICAL
            )
        )
        mBinding?.homeRecyclerView?.adapter = mAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mHomeViewModel?.mHomeArticleState?.collect { uiState: UiState ->
                    when (uiState) {
                        is UiState.Success<*> -> {
                            if (uiState.result == null) {
                                mAdapter?.setEmptyView(getEmptyDataView(mBinding?.homeRecyclerView))
                            } else {
                                val json = GsonUtils.toJson(uiState.result)
                                val articleData =
                                    GsonUtils.fromJson<JsonRootBean<PageData<Article>>>(
                                        json,
                                        object : TypeToken<JsonRootBean<PageData<Article>>>() {
                                        }.type
                                    )
                                if (articleData == null) {
                                    mAdapter?.loadMoreModule?.loadMoreEnd()
                                } else {
                                    mAdapter?.loadMoreModule?.loadMoreComplete()
                                }

                                if (mHomeViewModel?.isLoadMore == true) {
                                    articleData?.data?.datas?.let { mAdapter?.addData(it) }
                                } else {
                                    mAdapter?.setNewData(articleData?.data?.datas)
                                }
                            }
                        }

                        is UiState.Error<*> -> {
                            mAdapter?.setEmptyView(getErrorView(mBinding?.homeRecyclerView))
                        }

                        else -> {}
                    }

                }
            }

//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                mSharedViewModel?.menuArticleFlow?.collect {
//                    Log.i(TAG, "initRecyclerView: 接收数据")
//                    when (it) {
//                        "我的收藏" -> {
//                            Log.i(TAG, "initRecyclerView: 收到数据${it}")
//                            Navigation.findNavController(mBinding!!.root)
//                                .navigate(R.id.action_home_fragment_to_data_store_fragment)
//                            mBinding?.homeDrawer?.closeDrawer(GravityCompat.START)
//                        }
//                    }
//                }
//            }
        }

        mSharedViewModel?.menuJump?.observe(viewLifecycleOwner) {
            when (it) {
                DrawerFragment.title1 -> {
                    Log.i(TAG, "initRecyclerView: 收到数据${it}")
                    Navigation.findNavController(mBinding!!.root)
                        .navigate(R.id.action_home_fragment_to_data_store_fragment)
                    mBinding?.homeDrawer?.closeDrawer(GravityCompat.START)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: HomeFragment")
    }

    private fun initRefresh() {
        mBinding?.homeRefreshLayout?.setOnRefreshListener {
            mBinding?.homeRefreshLayout?.postDelayed({ getData() }, 1200)
            mBinding?.homeRefreshLayout?.finishRefresh(1500)
        }
    }

    /**
     * 下拉刷新数据
     */
    override fun getData() {
        mAdapter?.setEmptyView(getLoadingView(mBinding?.homeRecyclerView))
        currPage = 0
        mHomeViewModel?.queryHomeArticleList(currPage, false)
    }

    /**
     * 上拉加载数据
     */
    private fun loadMore() {
        currPage++
        mHomeViewModel?.queryHomeArticleList(currPage, true)
    }

    companion object {
        private const val TAG = "HomeFragment"
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(param1: String?, param2: String?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}