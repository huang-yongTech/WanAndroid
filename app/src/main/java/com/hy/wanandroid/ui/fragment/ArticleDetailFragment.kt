package com.hy.wanandroid.ui.fragment

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.hy.wanandroid.data.bean.Article
import com.hy.wanandroid.library.util.BarUtils
import com.hy.wanandroid.library.util.GsonUtils
import com.hy.wanandroid.ui.R
import com.hy.wanandroid.ui.click.PublicClickProxy
import com.hy.wanandroid.ui.databinding.FragmentArticleDetailBinding

/**
 * 文章详情界面fragment
 */
class ArticleDetailFragment : Fragment() {
    private var mArticleStr: String? = null
    private var mArticle: Article? = null

    private var mBinding: FragmentArticleDetailBinding? = null
    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mArticleStr = arguments?.getString(ARG_PARAM1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article_detail, container, false)
        mBinding = FragmentArticleDetailBinding.bind(view)
        BarUtils.setAppToolBarMarginTop(
            requireContext(), mBinding?.articleDetailInclude?.publicToolbar
        )
        initViews()
        return view
    }

    private fun initViews() {
        mArticle = GsonUtils.fromJson(mArticleStr, Article::class.java)
        Log.e(TAG, "initViews: mArticle = $mArticleStr")
        mBinding?.articleDetailInclude?.title = mArticle?.title
        mBinding?.articleDetailInclude?.clickProxy = PublicClickProxy()

        initWebView()
        loadData()
    }

    private fun initWebView() {
        mWebView = mBinding?.articleDetailWv
        val settings: WebSettings? = mWebView?.settings
        settings?.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        settings?.useWideViewPort = true
        settings?.cacheMode = WebSettings.LOAD_DEFAULT
        // 开启 DOM storage API 功能
        settings?.domStorageEnabled = true
        //设置自动换行及字体大小
        settings?.loadWithOverviewMode = true
        settings?.defaultFontSize = 16
        settings?.defaultTextEncodingName = "UTF-8"
        // 是否可访问Content Provider的资源，默认值 true
        settings?.allowContentAccess = true
        // 是否可访问本地文件，默认值 true
        settings?.allowFileAccess = false // 是否允许通过file url加载的Javascript读取本地文件，默认值 false

        // 支持缩放
        settings?.setSupportZoom(true)
        //开启 database storage API 功能
        settings?.databaseEnabled = true
        //设置显示图片
        settings?.blockNetworkImage = false
        //支持显示https 网址图片
        settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        //设置4.2以后版本支持autoPlay，非用户手势促发
        settings?.mediaPlaybackRequiresUserGesture = false
        settings?.loadsImagesAutomatically = true

        settings?.javaScriptEnabled = true
        settings?.javaScriptCanOpenWindowsAutomatically = true

        mWebView?.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView,
                handler: SslErrorHandler,
                error: SslError
            ) {
                handler.proceed()
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.e(TAG, "shouldOverrideUrlLoading: ${request?.url.toString()}")
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                Log.e(TAG, "shouldOverrideUrlLoading: $url")
                view?.loadUrl(url ?: "")
                return true
            }
        }

        mWebView?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    mBinding?.articleDetailPb?.visibility = View.GONE;
                } else {
                    mBinding?.articleDetailPb?.visibility = View.VISIBLE;
                    mBinding?.articleDetailPb?.progress = newProgress;
                }
            }
        }
    }

    private fun loadData() {
        Log.e(TAG, "loadData: url = ${mArticle?.link}")
        mWebView?.loadUrl(mArticle?.link ?: "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding?.articleDetailWv?.stopLoading()
        //webview清理内存
        mBinding?.articleDetailWv?.clearCache(true)
        //webview清理历史记录
        mBinding?.articleDetailWv?.clearHistory()
        mBinding?.articleDetailWv?.removeAllViews()
        //webview销毁
        mBinding?.articleDetailWv?.destroy()
    }

    companion object {
        private const val ARG_PARAM1 = "article"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(param1: String?, param2: String?): ArticleDetailFragment {
            val fragment = ArticleDetailFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }

        private const val TAG = "ArticleDetailFragment"
    }
}