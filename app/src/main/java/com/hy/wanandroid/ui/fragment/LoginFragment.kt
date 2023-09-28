package com.hy.wanandroid.ui.fragment

import com.hy.wanandroid.ui.viewmodel.LoginViewModel
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.library.util.BarUtils
import com.hy.wanandroid.ui.databinding.FragmentLoginBinding

/**
 * 登录界面
 */
class LoginFragment : Fragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mBinding: FragmentLoginBinding? = null
    private var mViewModel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParam1 = arguments?.getString(ARG_PARAM1)
        mParam2 = arguments?.getString(ARG_PARAM2)
        mViewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mBinding = FragmentLoginBinding.bind(view)
        mBinding?.vm = mViewModel
        BarUtils.setAppToolBarMarginTop(requireContext(), mBinding!!.loginInclude.publicToolbar)
        return view
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(param1: String?, param2: String?): LoginFragment {
            val fragment = LoginFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}