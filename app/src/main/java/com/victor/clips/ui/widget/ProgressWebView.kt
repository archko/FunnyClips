package com.victor.clips.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import android.content.Intent
import android.text.TextUtils
import android.graphics.Color
import android.net.Uri
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.webkit.*
import com.victor.clips.R
import com.victor.clips.util.Loger


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: ProgressWebView.java
 * Author: Victor
 * Date: 2019/11/6 10:30
 * Description: 带进度的WebView
 * -----------------------------------------------------------------
 */
class ProgressWebView constructor(context: Context, attributeset: AttributeSet) : ConstraintLayout(context, attributeset) {
    val TAG = "ProgressWebView"
    var progress: ProgressBar
    var webview: WebView
    var isLastLoadSuccess = false//是否成功加载完成过web，成功过后的网络异常 不改变web
    var isError = false

    init {
        val rootView = LayoutInflater.from(context).inflate(R.layout.web_progress_view, this, true)
        progress = rootView.findViewById(R.id.pb_webview)
        webview = rootView.findViewById(R.id.web_view)
        webview.webChromeClient = MyWebChromeClient()
        webview.webViewClient = MyWebViewClient()
        webview.settings.setJavaScriptEnabled(true)
        webview.settings.setDomStorageEnabled(true)
        webview.settings.cacheMode = WebSettings.LOAD_NO_CACHE
    }

    private inner class MyWebChromeClient : WebChromeClient() {

        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            setProgress(newProgress)
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            if (title.contains("html")) {
                return
            }
            listener?.onTitle(title)
        }
    }

    private fun setProgress(newProgress: Int) {
        if (newProgress == 100) {
            progress.visibility = View.GONE
        } else {
            progress.progress = newProgress
        }
    }

    private inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            Loger.e(TAG, "shouldOverrideUrlLoading()-url = $url")
            if (TextUtils.isEmpty(url)) return false
            try {
                if (url!!.startsWith("longtv://")) {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                    return true
                }
            } catch (e: Exception) {//防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                return true//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
            }
            //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
            view?.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            //在访问失败的时候会首先回调onReceivedError，然后再回调onPageFinished。
            if (!isError) {
                isLastLoadSuccess = true
                listener?.success()
            }
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            super.onReceivedError(view, request, error)
            //在访问失败的时候会首先回调onReceivedError，然后再回调onPageFinished。
            isError = true
            if (!isLastLoadSuccess) {//之前成功加载完成过，不会回调
                listener?.error()
            }
        }
    }

    /**
     * 千万不要更改这个 "SSDJsBirdge"  注意！！！！！
     */
    @SuppressLint("JavascriptInterface")
    fun addJavascriptInterface(jsInterface: Any) {
        webview.addJavascriptInterface(jsInterface, "SSDJsBirdge")
    }

    fun reload() {
        isError = false
        webview.reload()
    }

    fun loadUrl(url: String) {
        isError = false
        try {
            webview.loadUrl(url)
        } catch (e: Exception) {

        }

    }

    fun canGoBack(): Boolean {
        val canGoBack = webview.canGoBack()
        if (canGoBack) {
            webview.goBack()
        }
        return canGoBack
    }

    fun onPause () {
        webview.pauseTimers();

    }

    fun onResume () {
        webview.resumeTimers();
    }

    /**
     * must be called on the main thread
     */
    fun onDestory() {
        try {
            webview.clearHistory();
            webview.clearCache(true);
            webview.loadUrl("about:blank"); // clearView() should be changed to loadUrl("about:blank"), since clearView() is deprecated now
            webview.freeMemory();
            webview.pauseTimers();
            webview.destroy(); // Note that mWebView.destroy() and mWebView = null do the exact same thing
        } catch (e: Exception) {
        }

    }

    fun setWebViewBackgroundColor(isBlack: Boolean) {
        if (isBlack) {
            //防止加载html白屏(针对播放视频)
            setBackgroundColor(Color.BLACK)
        }
    }

    private var listener: OnWebLoadStatusListener? = null

    fun setOnLoadStatueListener(listener: OnWebLoadStatusListener) {
        this.listener = listener
    }

    interface OnWebLoadStatusListener {
        fun error()

        fun success()

        fun onTitle(title: String)
    }

}