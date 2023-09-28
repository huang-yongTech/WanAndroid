package com.hy.wanandroid.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hy.wanandroid.ui.DataModelPreference
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.ui.data.DataModelSerializer
import com.hy.wanandroid.ui.databinding.FragmentDataStoreTestBinding
import com.hy.wanandroid.ui.viewmodel.DataStoreViewModel
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DataStoreTestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataStoreTestFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var mBinding: FragmentDataStoreTestBinding? = null
    private var mViewModel: DataStoreViewModel? = null

    private val Context.dataStore: DataStore<DataModelPreference> by dataStore(
        fileName = PROTO_DATA_FILE_NAME,
        serializer = DataModelSerializer
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        mViewModel = ViewModelProvider(
            viewModelStore,
            defaultViewModelProviderFactory
        )[DataStoreViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_data_store_test, container, false)
        mBinding = FragmentDataStoreTestBinding.bind(view)
        initViews()
        return mBinding?.root
    }

    private fun initViews() {
        mBinding?.dataStoreAppbar?.publicTitleTv?.text = "DataStore测试"

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel?.dataModelFlow?.collect {
                    mBinding?.dataStoreTv?.text = "${it.name} ${it.age}"
                }
            }
        }

        mBinding?.dataStoreSaveBtn?.setOnClickListener {
            mViewModel?.updateText(dataStore = context?.dataStore)
        }
        mBinding?.dataStoreReadBtn?.setOnClickListener {
            mViewModel?.getText(dataStore = context?.dataStore)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DataStoreTestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        private const val PROTO_DATA_FILE_NAME: String = "data_model_preference.pb"
    }
}