package com.example.electricityandmagnetism.Screens

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.textSubTitleColor
import com.example.electricityandmagnetism.textTitleColor
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.rememberWebViewState
import kotlin.random.Random


/**Muestra la tarjeta del menú y en la sección de BusScreenManager que lo llama verifica si existe una configuración de ruta y turno
 * si no,el botón de onHomeCardClicked envía al usario a la pantalla de configuración*/
@Composable
fun HomeScreen(
    onHomeCardClicked: () -> Unit,
    appViewModel: AppViewModel
) {
    val appState by appViewModel.appState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeCard(
            onHomeCardClicked = onHomeCardClicked,
            volado = appState.volado
        )

        Text(text = "Latex equations:")

        LaTeXView(latex = "\\sin(x) \\cdot \\cos(y) \\cdot \\sin(x \\cdot y)")
        //LaTeXView(latex = "\\sin(x) \\cdot \\cos(y) \\cdot \\sin(x \\cdot y)")
        //LaTeXView(latex = "dT = \\nabla T \\cdot d\\vec{l} = (\\frac{\\partial T}{\\partial x} \\hat{x} + \\frac{\\partial T}{\\partial y} \\hat{y} + \\frac{\\partial T}{\\partial x} \\hat{x} ) \\cdot (dx \\hat{x} + dy \\hat{y} + dz \\hat{z})")

        /*LaTeXView(latex = "\\[ x^n + y^n = z^n \\]",
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.Green))
        )
        */


    }
}


@OptIn(ExperimentalMaterialApi::class) // Card is supposed to have an onclick function...
@Composable
fun HomeCard(
    onHomeCardClicked: () -> Unit,
    volado: Int
){
    val homeCardImage: Int = R.drawable.home_card_image2//if (volado == 0 ) R.drawable.home_card_image2 else R.drawable.home_card_image


    Card(
        elevation =5.dp,
        shape = RoundedCornerShape(15.dp),
        onClick = onHomeCardClicked,
    ) {
        Column() {
            Image(
                painter = painterResource(id = homeCardImage),
                contentDescription = stringResource(id = R.string.electricity_magnetism_home_card_image_description),//"Bus home image",
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(),
                    //.height(260.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(text = stringResource(id = R.string.electricity_magnetism_home_card_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp),
            )
            Text(text = stringResource(id = R.string.electricity_magnetism_home_card_description),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LaTeXView(
    latex: String,
    modifier: Modifier = Modifier
) {

    var webView: WebView? by remember { mutableStateOf(null) }

    val state = rememberWebViewState("file:///android_asset/latex_render.html")


    if (state.loadingState is LoadingState.Finished) {
        webView?.loadUrl("javascript:addBody('${latex.replace("\\", "\\\\")}')")
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
        }
    )
}