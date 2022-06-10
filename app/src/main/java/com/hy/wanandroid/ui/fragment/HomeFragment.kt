package com.hy.wanandroid.ui.fragment

import com.hy.wanandroid.library.base.BaseFragment
import com.hy.wanandroid.ui.viewmodel.HomeViewModel
import com.hy.wanandroid.ui.adapter.ArticleListAdapter
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.hy.wanandroid.data.bean.Article
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.library.widget.CustomLoadMoreView
import com.hy.wanandroid.library.widget.LinearItemDecoration
import com.hy.wanandroid.ui.databinding.FragmentHomeBinding

/**
 * 首页fragment
 */
class HomeFragment : BaseFragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mHomeViewModel: HomeViewModel? = null
    private var mBinding: FragmentHomeBinding? = null
    private var mAdapter: ArticleListAdapter? = null
    private var currPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParam1 = arguments?.getString(ARG_PARAM1)
        mParam2 = arguments?.getString(ARG_PARAM2)
        mHomeViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            HomeViewModel::class.java
        )
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
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
        mAdapter?.setOnItemClickListener { adapter, view, position -> }
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
        mHomeViewModel?.queryHomeArticleList(currPage)
            ?.observe(viewLifecycleOwner, Observer { pageDataJsonRootBean ->
                if (pageDataJsonRootBean != null) {
                    mAdapter?.setNewData(pageDataJsonRootBean.data?.datas)
                } else {
                    mAdapter?.setEmptyView(getEmptyDataView(mBinding?.homeRecyclerView))
                }
            })
    }

    /**
     * 上拉加载数据
     */
    private fun loadMore() {
        currPage++
        mHomeViewModel?.queryHomeArticleList(currPage)
            ?.observe(viewLifecycleOwner, Observer { pageDataJsonRootBean ->
                if (pageDataJsonRootBean != null) {
                    pageDataJsonRootBean.data?.datas?.let { mAdapter?.addData(it) }
                    mAdapter?.loadMoreModule?.loadMoreComplete()
                } else {
                    mAdapter?.loadMoreModule?.loadMoreEnd()
                }
            })
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