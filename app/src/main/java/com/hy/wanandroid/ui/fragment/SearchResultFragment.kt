package com.hy.wanandroid.ui.fragment

import com.hy.wanandroid.library.base.BaseFragment
import com.hy.wanandroid.ui.viewmodel.SearchResultViewModel
import com.hy.wanandroid.ui.adapter.ArticleListAdapter
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.ui.click.PublicClickProxy
import com.hy.wanandroid.library.util.BarUtils
import com.hy.wanandroid.library.widget.CustomLoadMoreView
import com.hy.wanandroid.library.widget.LinearItemDecoration
import androidx.lifecycle.Observer
import com.hy.wanandroid.library.util.Constant
import com.hy.wanandroid.ui.databinding.FragmentSearchResultBinding
import java.util.*

/**
 * 搜索结果界面
 */
class SearchResultFragment : BaseFragment() {
    private var mSearchKey: String? = null
    private var mViewModel: SearchResultViewModel? = null
    private var mBinding: FragmentSearchResultBinding? = null
    private var mAdapter: ArticleListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSearchKey = arguments?.getString(Constant.TYPE_SEARCH_KEY)
        mViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            SearchResultViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_result, container, false)
        mBinding = FragmentSearchResultBinding.bind(view)
        mBinding?.vm = mViewModel
        mBinding?.searchResultInclude?.clickProxy = PublicClickProxy()
        mBinding?.searchResultInclude?.title = mSearchKey
        BarUtils.setAppToolBarMarginTop(context, mBinding?.searchResultInclude?.publicToolbar)
        initRefreshLayout()
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun initRefreshLayout() {
        mBinding?.searchResultRefreshLayout?.setOnRefreshListener {
            mBinding?.searchResultRefreshLayout?.postDelayed({ getData() }, 1200)
            mBinding?.searchResultRefreshLayout?.finishRefresh(1500)
        }
    }

    private fun initRecyclerView() {
        mAdapter = ArticleListAdapter()
        mAdapter?.loadMoreModule?.loadMoreView = CustomLoadMoreView()
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        mAdapter?.loadMoreModule?.isEnableLoadMoreIfNotFullPage = false
        mAdapter?.loadMoreModule?.setOnLoadMoreListener { loadMore() }
        mAdapter?.setOnItemClickListener { adapter, view, position -> }
        mBinding?.searchResultRecyclerView?.addItemDecoration(
            LinearItemDecoration(requireContext(), LinearItemDecoration.VERTICAL)
        )
        mBinding?.searchResultRecyclerView?.adapter = mAdapter
    }

    /**
     * 加载更多
     */
    private fun loadMore() {}
    override fun getData() {
        mAdapter?.setEmptyView(getLoadingView(mBinding?.searchResultRecyclerView))
        mViewModel?.queryArticlesByKey(0, mSearchKey)
            ?.observe(viewLifecycleOwner) { pageDataJsonRootBean ->
                if (pageDataJsonRootBean != null) {
                    mAdapter?.setNewData(pageDataJsonRootBean.data?.datas)
                    if (pageDataJsonRootBean.data?.pageCount == 1) {
                        mAdapter?.loadMoreModule?.loadMoreEnd()
                    }
                } else {
                    mAdapter?.setEmptyView(getEmptyDataView(mBinding?.searchResultRecyclerView))
                }
            }
    }

    companion object {
        private const val TAG = "SearchResultFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(searchKey: String?): SearchResultFragment {
            val fragment = SearchResultFragment()
            val args = Bundle()
            args.putString(Constant.TYPE_SEARCH_KEY, searchKey)
            fragment.arguments = args
            return fragment
        }
    }
}