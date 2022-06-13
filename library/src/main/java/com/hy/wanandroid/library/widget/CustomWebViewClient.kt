package com.hy.wanandroid.library.widget

import android.webkit.WebViewClient
import android.graphics.Bitmap
import android.webkit.WebView

/**
 * 自定义WebViewClient，设置可以在WebView中点击图片查看相应的大图
 */
class CustomWebViewClient : WebViewClient() {
    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
        view.settings.javaScriptEnabled = true
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView, url: String) {
        view.settings.javaScriptEnabled = true
        super.onPageFinished(view, url)
        addImageClickListener(view)
    }

    private fun addImageClickListener(webView: WebView) {

        //遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl(
            "javascript:(function(){" +
                    "var objs = document.getElementsByTagName(\"img\"); " +
                    "for(var i=0;i<objs.length;i++)  " +
                    "{"
                    + "    objs[i].onclick=function()  " +
                    "    {  "
                    + "        window.imageListener.openImage(this.src);  " +
                    "    }  " +
                    "}" +
                    "})()"
        )
    }
}