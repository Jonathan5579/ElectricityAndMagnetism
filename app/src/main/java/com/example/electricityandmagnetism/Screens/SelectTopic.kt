package com.example.electricityandmagnetism.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.R


@Composable
fun SelectTopicScreen(
    appViewModel: AppViewModel,
    navigateFundamentalsScreen: () -> Unit,
    navigateGriffithsScreen: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        LayersGrid(
            appViewModel = appViewModel,
            navigateFundamentalsScreen = navigateFundamentalsScreen,
            navigateGriffithsScreen = navigateGriffithsScreen
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LayersGrid(
    appViewModel: AppViewModel,
    navigateFundamentalsScreen: ()->Unit,
    navigateGriffithsScreen: () -> Unit
    //areaLayer: MutableList<Pair<String, String>>, //recibe un alista de tuplas de area y layer
) {
    //val appState by appViewModel.appState.collectAsState()

    QuantumCardElement(
        appViewModel = appViewModel,
        navigateNextScreen = navigateFundamentalsScreen,
        cardTitle = "Fundamentals",
        cardDescription = "Basic theory about electricity and magnetism",
        drawableId = R.drawable.electric_charges_icon,
        //backgroundColor = 0xFF2A43A9
    )

    QuantumCardElement(
        appViewModel = appViewModel,
        navigateNextScreen = navigateGriffithsScreen,
        cardTitle = "Griffiths",
        cardDescription = "Topics about griffhts",
        drawableId = R.drawable.griffiths_icon,
        iconBackgroundColor = Color(0xFF000000)
    )

    QuantumCardElement(
        appViewModel = appViewModel,
        navigateNextScreen = {  },
        cardTitle = "?",
        cardDescription = "...",
    )

    QuantumCardElement(
        appViewModel = appViewModel,
        navigateNextScreen = {  },
        cardTitle = "?",
        cardDescription = "...",
    )
    LaTeXView(latex = "\\sin(x) \\cdot \\cos(y) \\cdot \\sin(x \\cdot y)")

    /*
    LazyVerticalGrid(
        //modifier = Modifier.fillMaxHeight(0.8f),
        //columns = GridCells.Fixed(2),
        columns = GridCells.Adaptive(minSize = 180.dp),
        //contentPadding = PaddingValues(10.dp)
    ) {

        item {
            cardElementDesechable(
                appViewModel = appViewModel,
                navigateNextScreen = navigateNextScreen,
                cardTitle = "Fundamentals",
                cardDescription = "Basic theory about electricity and magnetism",
                drawableId = R.drawable.electric_field
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

    }
    */
}

@ExperimentalMaterialApi
@Composable
fun QuantumCardElement(
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit,
    cardTitle: String,
    cardDescription: String,
    drawableId: Int = R.drawable.imageplaceholder,
    iconBackgroundColor: Color = Color.Transparent
){
    Card(
        elevation = 0.dp,
        onClick = {
            navigateNextScreen()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            QuantumTopicCardImage(
                drawableId = drawableId,
                iconBackgroundColor = iconBackgroundColor
            )

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = cardTitle,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = cardDescription,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    //color = Color(0xFF464646),
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
    Spacer(modifier = Modifier
        .fillMaxWidth(0.9f)
        .height(1.dp)
        .background(Color(if (isSystemInDarkTheme()) 0xFF313131 else 0xFFE2E2E2))
    )
}



@Composable
fun QuantumTopicCardImage(
    drawableId: Int,
    iconBackgroundColor: Color
    //backgroundColor: Long
){
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(39.dp),
        backgroundColor = iconBackgroundColor
        //backgroundColor = Color(backgroundColor)
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
                .size(90.dp)

            //.padding(20.dp)//.padding(15.dp)
        )
    }

}









/*
@ExperimentalMaterialApi
@Composable
fun cardElementDesechable(
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit,
    cardTitle: String,
    cardDescription: String,
    drawableId: Int = R.drawable.imageplaceholder
){
    Card(
        //modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)
        elevation = 8.dp,
        shape = RoundedCornerShape(11.dp),
        onClick = {
            navigateNextScreen()
        }
    ) {
        Box(
        ){
            Column(
                modifier = Modifier
                    //.padding(15.dp)
                    .align(Alignment.Center)
                    .background(Color(0xFF3F677A)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                topicCardImage(drawableId)

                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = cardTitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = Color(0xFFFFFFFF)
                    )
                    Text(text = cardDescription,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = Color(0xFFE2E2E2)
                    )
                }

            }
        }
    }
}




@Composable
fun topicCardImage(
    drawableId: Int
){
    Box(
    ){
        Card(
            elevation = 3.dp,
            shape = RoundedCornerShape(8.dp),
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
*/