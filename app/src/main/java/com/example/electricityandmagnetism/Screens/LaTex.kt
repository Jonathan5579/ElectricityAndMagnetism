package com.example.electricityandmagnetism.Screens

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LaTeXView(
    latex: String,
    modifier: Modifier = Modifier,
    textColor: String = if(isSystemInDarkTheme()) "#dedede" else "#4f4f4f",
) {

    var webView: WebView? by remember { mutableStateOf(null) }

    val state = rememberWebViewState("file:///android_asset/latex_render.html")

    if (state.loadingState is LoadingState.Finished) {
        webView?.loadUrl("javascript:addBody('${latex.replace("\\", "\\\\")}')")
        webView?.loadUrl("javascript:document.body.style.setProperty(\"color\", \"${textColor}\");")
        //webView?.loadUrl("javascript:document.body.style.setProperty(\"fontsize\", \"${textSize}\");")
    }

    //myWebView.getSettings().setUseWideViewPort(true);
    //myWebView.getSettings().setLoadWithOverviewMode(true);
    com.google.accompanist.web.WebView(
        state = state,
        modifier = modifier,
        onCreated = {
            //it.settings.useWideViewPort = true
            //it.settings.loadWithOverviewMode = true

            it.settings.javaScriptEnabled = true
            webView = it
            //it.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)
            it.setBackgroundColor(0)

        },

        )
}