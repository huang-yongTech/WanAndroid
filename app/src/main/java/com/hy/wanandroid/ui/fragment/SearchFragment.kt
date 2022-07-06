package com.hy.wanandroid.ui.fragment

import com.hy.wanandroid.library.base.BaseFragment
import com.hy.wanandroid.ui.viewmodel.SearchViewModel
import com.hy.wanandroid.ui.adapter.SearchKeyAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.library.util.BarUtils
import android.text.TextWatcher
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.google.gson.reflect.TypeToken
import com.hy.wanandroid.data.bean.HotWord
import com.hy.wanandroid.data.bean.JsonListRootBean
import com.hy.wanandroid.data.state.UiState
import com.hy.wanandroid.library.util.GsonUtils
import com.hy.wanandroid.ui.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 查找界面fragment
 */
class SearchFragment : BaseFragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mSearchViewModel: SearchViewModel? = null
    private var mBinding: FragmentSearchBinding? = null
    private var mHistoryAdapter: SearchKeyAdapter? = null
    private var mHotKeyAdapter: SearchKeyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParam1 = arguments?.getString(ARG_PARAM1)
        mParam2 = arguments?.getString(ARG_PARAM2)
        mSearchViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(
            SearchViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        mBinding = FragmentSearchBinding.bind(view)
        mBinding?.vm = mSearchViewModel
        BarUtils.setAppToolBarMarginTop(requireContext(), mBinding?.searchToolbar)
        initView()
        initAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun initView() {
        mBinding?.searchKeyWordEt?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mBinding?.searchKeyWordEt?.isSelected = !TextUtils.isEmpty(s)
                if (mBinding?.searchKeyWordEt?.text != null) {
                    mBinding?.searchKeyWordEt?.text?.length?.let {
                        mBinding?.searchKeyWordEt?.setSelection(
                            it
                        )
                    }
                }
                mSearchViewModel?.mSearchEnabled?.set(!TextUtils.isEmpty(s))
            }

            override fun afterTextChanged(s: Editable) {}
        })
        clickRightClear(mBinding?.searchKeyWordEt)
    }

    private fun initAdapter() {
        mHistoryAdapter = SearchKeyAdapter()
        mBinding?.searchHistoryRecyclerView?.adapter = mHistoryAdapter
        mHistoryAdapter?.setNewData(mSearchViewModel?.mHistoryKeyList?.get())
        mHotKeyAdapter = SearchKeyAdapter()
        mBinding?.searchHotWordRecyclerView?.adapter = mHotKeyAdapter
        mHistoryAdapter?.setOnItemClickListener { adapter, view, position ->
            val name = mHistoryAdapter?.data?.get(position)?.name
            mSearchViewModel?.mSearchKey?.set(name)
        }
        mHotKeyAdapter?.setOnItemClickListener { adapter, view, position ->
            val name = mHotKeyAdapter?.data?.get(position)?.name
            mSearchViewModel?.mSearchKey?.set(name)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mSearchViewModel?.hotWordsState?.collect {
                    when (it) {
                        is UiState.Success<*> -> {
                            val json = GsonUtils.toJson(it.result)
                            val hotWordsData = GsonUtils.fromJson<JsonListRootBean<HotWord?>>(
                                json,
                                object : TypeToken<JsonListRootBean<HotWord?>>() {}.type
                            )
                            mHotKeyAdapter?.setNewData(hotWordsData?.data)
                        }
                        is UiState.Error<*> -> {

                        }
                    }
                }
            }
        }
    }

    override fun getData() {
        mSearchViewModel?.getHistoryKey()?.observe(
            viewLifecycleOwner, Observer<MutableList<HotWord?>?> {
                mHistoryAdapter?.setNewData(it)
            }
        )

        mSearchViewModel?.getHotWords()
//        (
//            viewLifecycleOwner
//        ) { hotWordJsonListRootBean ->
//            mHotKeyAdapter?.setNewData(hotWordJsonListRootBean?.data)
//        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(param1: String?, param2: String?): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}