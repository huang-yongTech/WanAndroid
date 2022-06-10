package com.hy.wanandroid.ui.activity

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hy.wanandroid.ui.R
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //APP灰度设置（主要是设置饱和度，饱和度为0就是灰度，饱和度为1就是正常颜色）
//        Paint paint = new Paint();
//        ColorMatrix cm = new ColorMatrix();
//        cm.setSaturation(0);
//        paint.setColorFilter(new ColorMatrixColorFilter(cm));
//        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, paint);
        setContentView(R.layout.activity_main)
        //        DataBindingUtil.setContentView(this, R.layout.activity_main);

        //5.0 全透明实现，即侧滑栏滑动时，状态栏与侧滑栏交界处透明
        val window = window
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}