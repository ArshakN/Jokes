package com.acn.jokesapp.views

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.acn.jokesapp.R
import com.acn.jokesapp.util.INFO_URL
import com.acn.jokesapp.viewmodels.InfoViewModel
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment(R.layout.fragment_info) {
    private val infoViewModel by navGraphViewModels<InfoViewModel>(R.id.nav_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setWebView(INFO_URL)
        observe()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView(url: String) {
        webView.apply {
            settings.setSupportZoom(true)
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack() && event.action == KeyEvent.ACTION_DOWN) {
                    webView.goBack()
                    true
                } else
                    false
            }
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    infoViewModel.isLoading.value = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    infoViewModel.isLoading.value = false
                }
            }
            loadUrl(url)
        }
    }

    private fun observe() {
        infoViewModel.isLoading.observe(viewLifecycleOwner,
            Observer {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            })
    }
}

