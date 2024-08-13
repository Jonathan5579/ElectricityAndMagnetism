package com.example.electricityandmagnetism.Screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.R


/**Muestra la tarjeta del menú y en la sección de BusScreenManager que lo llama verifica si existe una configuración de ruta y turno
 * si no,el botón de onHomeCardClicked envía al usario a la pantalla de configuración*/
@Composable
fun HomeScreen(
    onHomeCardClicked: () -> Unit,
    appViewModel: AppViewModel
) {
    val appState by appViewModel.appState.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeCard(
            onHomeCardClicked = onHomeCardClicked,
            volado = appState.volado
        )
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
        elevation = 5.dp,
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

            Text(
                text = stringResource(id = R.string.electricity_magnetism_home_card_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp),
            )
            Text(
                text = stringResource(id = R.string.electricity_magnetism_home_card_description),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            )
        }
    }
}