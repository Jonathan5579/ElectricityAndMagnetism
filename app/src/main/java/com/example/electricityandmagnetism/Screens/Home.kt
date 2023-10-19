package com.example.electricityandmagnetism.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


/**Muestra la tarjeta del menú y en la sección de BusScreenManager que lo llama verifica si existe una configuración de ruta y turno
 * si no,el botón de onHomeCardClicked envía al usario a la pantalla de configuración*/
@Composable
fun HomeScreen(
    onHomeCardClicked: () -> Unit,
    appViewModel: AppViewModel
) {
    val lateralWeight = 8f
    val contentWeight = 100 - lateralWeight

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.height(30.dp))

        Row() {
            Spacer(modifier = Modifier.weight(lateralWeight))

            Box(modifier = Modifier.weight(contentWeight)) {
                HomeCard(
                    onHomeCardClicked = onHomeCardClicked,
                )
            }

            Spacer(modifier = Modifier.weight(lateralWeight))
        }
    }
}


@OptIn(ExperimentalMaterialApi::class) // Card is supposed to have an onclick function...
@Composable
fun HomeCard(
    onHomeCardClicked: () -> Unit
){
    Card(
        elevation =10.dp,
        shape = RoundedCornerShape(15.dp),
        onClick = onHomeCardClicked,
        backgroundColor = Color(0xFFFAFAFA)
    ) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.vectorial_field),
                contentDescription = stringResource(id = R.string.electricity_magnetism_home_card_image_description),//"Bus home image",
                modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )

            Text(text = stringResource(id = R.string.electricity_magnetism_home_card_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp),
                color = Color(0xFF000000)
            )
            Text(text = stringResource(id = R.string.electricity_magnetism_home_card_description),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                color = Color(0xFF5A5A5A)
            )
        }
    }
}
