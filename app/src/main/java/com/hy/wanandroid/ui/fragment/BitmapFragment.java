package com.hy.wanandroid.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.wanandroid.ui.R;

import java.util.Objects;

/**
 * 测试图片占用内存fragment
 */
public class BitmapFragment extends Fragment {
    private static final String TAG = "BitmapFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Bitmap mBitmap;

    public BitmapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static BitmapFragment newInstance(String param1, String param2) {
        BitmapFragment fragment = new BitmapFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bitmap, container, false);
        AppCompatImageView imageView = view.findViewById(R.id.test_english_img);
        mBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Log.i(TAG, "mBitmap size: " + mBitmap.getByteCount());

        //通过SharedPreferences共享数据
        SharedPreferences sp = requireContext().getSharedPreferences("shared_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("data1", "this is data1");
        editor.putString("data2", "this is data2");
        editor.putString("data3", "this is data3");
        editor.putString("data4", "this is data4");
        editor.putString("data5", "this is data5");
        editor.apply();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBitmap != null) {
            mBitmap.recycle();
        }
    }
}