package com.hy.wanandroid.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hy.wanandroid.ui.R
import androidx.appcompat.widget.AppCompatImageView
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

/**
 * 测试图片占用内存fragment
 */
class BitmapFragment : Fragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    private var mBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParam1 = arguments?.getString(ARG_PARAM1)
        mParam2 = arguments?.getString(ARG_PARAM2)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bitmap, container, false)
        val imageView: AppCompatImageView = view.findViewById(R.id.test_english_img)
        mBitmap = (imageView.drawable as BitmapDrawable).bitmap
        Log.i(TAG, "mBitmap size: " + mBitmap?.byteCount)

        //通过SharedPreferences共享数据
        val sp = requireContext().getSharedPreferences("shared_data", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("data1", "this is data1")
        editor.putString("data2", "this is data2")
        editor.putString("data3", "this is data3")
        editor.putString("data4", "this is data4")
        editor.putString("data5", "this is data5")
        editor.apply()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mBitmap != null) {
            mBitmap?.recycle()
        }
    }

    companion object {
        private const val TAG = "BitmapFragment"
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(param1: String?, param2: String?): BitmapFragment {
            val fragment = BitmapFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}