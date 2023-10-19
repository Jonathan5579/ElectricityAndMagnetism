package com.example.electricityandmagnetism.Screens.Fundamentals

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.Screens.cardElementDesechable
import com.example.electricityandmagnetism.Screens.layersGrid
import com.example.electricityandmagnetism.textSubTitleColor
import com.example.electricityandmagnetism.textTitleColor

@Composable
fun Fundamentals(
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit
) {
    val lateralWeight = 8f
    val contentWeight = 100 - lateralWeight

    Column(
        modifier = Modifier
            .padding(horizontal = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(0.dp))

        FundamentalsColumn(
            navigateNextScreen = navigateNextScreen,
            appViewModel = appViewModel)

        Spacer(modifier = Modifier.height(40.dp))
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FundamentalsColumn(
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit
){

    val appState by appViewModel.appState.collectAsState()

/*
    Text(
        "Glossary",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp, start = 20.dp, top =15.dp),
        color = Color.White
    )
    */
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {

        FundamentalsCard(
            appViewModel = appViewModel,
            navigateNextScreen = navigateNextScreen,
            cardTitle = "Electric charges",
            cardDescription = "La flsmdpsff",
        )

        FundamentalsCard(
            appViewModel = appViewModel,
            navigateNextScreen = navigateNextScreen,
            cardTitle = "Electric Field",
            cardDescription = "...",
        )

        FundamentalsCard(
            appViewModel = appViewModel,
            navigateNextScreen = navigateNextScreen,
            cardTitle = "Electric field at a point p",
            cardDescription = "...",
        )

        HorizontalBreak()

        FundamentalsCard(
            appViewModel = appViewModel,
            navigateNextScreen = navigateNextScreen,
            cardTitle = "Electrostatic Potential Energy",
            cardDescription = "...",
        )

        FundamentalsCard(
            appViewModel = appViewModel,
            navigateNextScreen = navigateNextScreen,
            cardTitle = "Electrostatic Potential",
            cardDescription = "...",
        )

    }

    /*
    LazyVerticalGrid(
        //modifier = Modifier.fillMaxHeight(0.8f),
        //columns = GridCells.Fixed(2),
        columns = GridCells.Adaptive(minSize = 180.dp),
        contentPadding = PaddingValues(10.dp)
    ) {

        item {
            FundamentalsCard(
                appViewModel = appViewModel,
                navigateNextScreen = navigateNextScreen,
                cardTitle = "Electric charges",
                cardDescription = "La flsmdpsff",
            )
        }

        item {
            FundamentalsCard(
                appViewModel = appViewModel,
                navigateNextScreen = navigateNextScreen,
                cardTitle = "Electric Field",
                cardDescription = "...",
            )
        }
    }

     */
}




@ExperimentalMaterialApi
@Composable
fun FundamentalsCard(
    cardTitle: String,
    cardDescription: String,
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit
){
    Card(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),//start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(11.dp),
        onClick = {
            //appViewModel.retrieveAuditQuestions(format.ID)
            navigateNextScreen()
        }
    ) {
        Box(
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterStart)
                    .background(Color(0xFFFFFFFF))
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = cardTitle,
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000)

                )

                Row(
                ) {

                    FundamentalsImage(R.drawable.electric_field)

                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                    ) {

                        Text(text = cardDescription,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF5A5A5A)
                        )
                    }

                }
            }

        }
    }
}




@Composable
fun FundamentalsImage(
    drawableId: Int
){
    Box(
    ){
        Card(
            shape = RoundedCornerShape(2.dp),
            //modifier = Modifier.fillMaxWidth(),//.background(Color(0xFFFF2D2D)),
            //backgroundColor = Color(0xFF136083)//0xFF136083
        ) {
            /*
            Icon(
                modifier = Modifier
                    .size(95.dp)
                    .padding(20.dp),//.padding(15.dp)
                tint = Color(0xFFFFFFFF),
                imageVector = Icons.Default.Search,
                contentDescription = "icondescription"
            )
            */
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = "layer icon description",

                contentScale = ContentScale.FillBounds, // crop the image if it's not a square
                modifier = Modifier
                    .size(150.dp)
                //.padding(20.dp)//.padding(15.dp)
            )
        }
    }
}


@Composable
fun HorizontalBreak(){

    Spacer(modifier = Modifier
        .height(50.dp)
    )

    Spacer(modifier = Modifier
        .height(2.dp)
        .fillMaxWidth()
        .background(Color.Black)
    )

    Spacer(modifier = Modifier
        .height(50.dp)
    )
}