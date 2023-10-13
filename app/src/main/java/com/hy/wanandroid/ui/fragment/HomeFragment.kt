package com.hy.wanandroid.ui.fragment

import com.hy.wanandroid.library.base.BaseFragment
import com.hy.wanandroid.ui.viewmodel.HomeViewModel
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
import androidx.paging.LoadState
import com.hy.wanandroid.library.util.ToastUtils
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.library.widget.LinearItemDecoration
import com.hy.wanandroid.ui.adapter.ArticleAdapter
import com.hy.wanandroid.ui.adapter.FootAdapter
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
    private var mArticleAdapter: ArticleAdapter? = null

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
        mArticleAdapter = ArticleAdapter(requireContext()) { position, article, adapter ->
            ToastUtils.showToast(article?.title)
        }
        mArticleAdapter?.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    Log.e(TAG, "initAdapter: NotLoading")
                }

                is LoadState.Loading -> {
                    Log.e(TAG, "initAdapter: Loading")
                }

                is LoadState.Error -> {
                    Log.e(TAG, "initAdapter: Error")
                }

                else -> {}
            }
        }
    }

    private fun initRecyclerView() {
        mBinding?.homeRecyclerView?.addItemDecoration(
            LinearItemDecoration(
                requireContext(),
                LinearItemDecoration.VERTICAL
            )
        )
        val withLoadStateAdapter =
            mArticleAdapter?.withLoadStateFooter(FootAdapter(requireContext()))
        mBinding?.homeRecyclerView?.adapter = withLoadStateAdapter

        /**
         * 注意：
         * 当使用共享ViewModel配合SharedFlow跨Fragment发射和接收数据时，在fragment里面，
         * 外层的生命周期Scope需要使用lifecycleScope而不是viewLifecycleOwner.lifecycleScope。
         * 这是因为viewLifecycleOwner.lifecycleScope是和Fragment关联的View生命周期绑定，
         * lifecycleScope是和Fragment生命周期绑定。
         */
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mSharedViewModel?.menuArticleFlow?.collect {
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
        viewLifecycleOwner.lifecycleScope.launch {
            mHomeViewModel?.getArticleList()?.collect {
                mArticleAdapter?.submitData(it)
            }
        }
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