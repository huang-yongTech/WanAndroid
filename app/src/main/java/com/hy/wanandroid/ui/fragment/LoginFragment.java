package com.hy.wanandroid.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.wanandroid.library.util.BarUtils;
import com.hy.wanandroid.ui.R;
import com.hy.wanandroid.ui.databinding.FragmentLoginBinding;
import com.hy.wanandroid.ui.viewmodel.LoginViewModel;

/**
 * 登录界面
 */
public class LoginFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FragmentLoginBinding mBinding;
    private LoginViewModel mViewModel;

    public LoginFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
                .get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mBinding = FragmentLoginBinding.bind(view);
        mBinding.setVm(mViewModel);

        BarUtils.setAppToolBarMarginTop(getContext(), mBinding.loginInclude.publicToolbar);
        return view;
    }
}
