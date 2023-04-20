package com.hy.wanandroid.ui.activity

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.hy.wanandroid.ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //APP灰度设置（主要是设置饱和度，饱和度为0就是灰度，饱和度为1就是正常颜色）
//        val paint = Paint()
//        val cm = ColorMatrix()
//        cm.setSaturation(0f)
//        paint.colorFilter = ColorMatrixColorFilter(cm)
//        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //5.0 全透明实现，即侧滑栏滑动时，状态栏与侧滑栏交界处透明
//        val controller = WindowCompat.getInsetsController(window, mBinding.root)
        //状态栏字体颜色为白色
//        controller.isAppearanceLightStatusBars = false
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}