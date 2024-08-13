package com.example.electricityandmagnetism.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.FundamentalCard
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.Screens.Electrostatic.buildElectrostaticContent
import com.example.electricityandmagnetism.Screens.Griffiths.buildMathBasicsContent
import com.example.electricityandmagnetism.ui.theme.LocalCustomColorsPalette


@Composable
fun Electrostatic(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        electrostaticTitle()

        electrostaticContent()

        Spacer(modifier = Modifier.height(50.dp))

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
private fun electrostaticTitle(
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(LocalCustomColorsPalette.current.defaultMathBasicsTitleBackground)
        ){
            Card(
                modifier = Modifier
                    .padding(50.dp)
                    .align(Alignment.Center),
                elevation = 0.dp,
                shape = RoundedCornerShape(80.dp),
                backgroundColor = Color(0xFF1D1D1D)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vector_icon),
                    contentDescription = "layer icon description",
                    contentScale = ContentScale.Fit, // crop the image if it's not a square
                    modifier = Modifier.size(280.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFE7E7E7))
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun electrostaticContent(){
    val fundamentalCards = buildElectrostaticContent()

    for (card in fundamentalCards){

        electroStaticsCard(
            fundamentalCard = card,
            contentComposable = card.contentComposable
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
private fun electroStaticsCard(
    fundamentalCard: FundamentalCard,
    contentComposable: @Composable () -> Unit = {}
){
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp),
        /*modifier = Modifier.combinedClickable(
            onClick = { },
            onLongClick = {
                appViewModel.updateShowArrowButtons()
            },
        ),*/
        backgroundColor = Color.Transparent
    ) {
        Box(
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "${fundamentalCard.title}", color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 20.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(2.dp)
                    .background(Color(0xFF969696)))
                Spacer(modifier = Modifier.height(16.dp))

                contentComposable()

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}