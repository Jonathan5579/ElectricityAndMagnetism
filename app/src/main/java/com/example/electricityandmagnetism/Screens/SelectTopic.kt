package com.example.electricityandmagnetism.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.textSubTitleColor
import com.example.electricityandmagnetism.textTitleColor


@Composable
fun SelectTopicScreen(
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit
) {
    val lateralWeight = 8f
    val contentWeight = 100 - lateralWeight

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        layersGrid(navigateNextScreen = navigateNextScreen, appViewModel = appViewModel)

        Spacer(modifier = Modifier.height(40.dp))
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun layersGrid(
    navigateNextScreen: ()->Unit,
    appViewModel: AppViewModel
    //areaLayer: MutableList<Pair<String, String>>, //recibe un alista de tuplas de area y layer
) {
    val appState by appViewModel.appState.collectAsState()

    Text(
        "Glossary",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp, start = 20.dp, top =15.dp)
    )

    LazyVerticalGrid(
        //modifier = Modifier.fillMaxHeight(0.8f),
        //columns = GridCells.Fixed(2),
        columns = GridCells.Adaptive(minSize = 180.dp),
        contentPadding = PaddingValues(10.dp)
    ) {

        item {
            cardElementDesechable(
                appViewModel = appViewModel,
                navigateNextScreen = navigateNextScreen,
                cardTitle = "Fundamentals",
                cardDescription = "Basic theory about electricity and magnetism",
            )
        }

        item {
            cardElementDesechable(
                appViewModel = appViewModel,
                navigateNextScreen = navigateNextScreen,
                cardTitle = "?",
                cardDescription = "...",
            )
        }

        /*
        for (format in appState.areaLayer){
            item {
                cardElementDesechable(
                    appViewModel = appViewModel,
                    navigateNextScreen = navigateNextScreen,
                    cardTitle = "Fundamentals",
                    cardDescription = "Basic theory about electricity and magnetism",
                )
            }
        }
        */
    }
}


@ExperimentalMaterialApi
@Composable
fun cardElementDesechable(
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
                    //.padding(15.dp)
                    .align(Alignment.Center)
                    .background(Color(0xFF4D4D4D)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                layerImage(R.drawable.electric_field)

                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = cardTitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = Color(textTitleColor)
                    )
                    Text(text = cardDescription,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = Color(textSubTitleColor)
                    )
                }

            }
        }
    }
}




@Composable
fun layerImage(
    drawableId: Int
){
    Box(
    ){
        Card(
            elevation = 3.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),//.background(Color(0xFFFF2D2D)),
            backgroundColor = Color(0xFF136083)//0xFF136083
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
                    .size(130.dp)
                    //.padding(20.dp)//.padding(15.dp)
            )
        }
    }
}