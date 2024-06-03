package com.example.electricityandmagnetism.Screens.Griffiths

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.FundamentalCard
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.RDrawableElectricCharges
import com.example.electricityandmagnetism.RDrawableElectricField
import com.example.electricityandmagnetism.Screens.Fundamentals.FundamentalCardContentText
import com.example.electricityandmagnetism.Screens.Fundamentals.FundamentalCardTitleText
import com.example.electricityandmagnetism.Screens.Fundamentals.moveCardArrows
import com.example.electricityandmagnetism.Screens.LaTeXView
import com.example.electricityandmagnetism.getDrawableFromFundamentalCard
import com.example.electricityandmagnetism.ui.theme.LocalCustomColorsPalette

@Composable
fun MathBasicsScreen(
    appViewModel: AppViewModel
){
    val appState by appViewModel.appState.collectAsState()

    /*
    if (appState.showRawJson){
        RawJsonScreen(
            appViewModel = appViewModel,
            dataStore = dataStore
        )
        return
    }
    */

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        mathBasicsTitle()

        mathbasicsContent(
            appViewModel = appViewModel
        )

        Spacer(modifier = Modifier.height(50.dp))

        //AddSectionIcon(appViewModel)

        Spacer(modifier = Modifier.height(80.dp))
    }
/*
    SaveContentFloatingButton(
        appViewModel = appViewModel,
        dataStore = dataStore
    )
    */
    /*
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.BottomEnd
    ){
        Button(onClick = { appViewModel.sortFundamentalCardsByItemOrdering() }) {
            Text("Sort")
        }
    }
    */
}

@Composable
private fun mathBasicsTitle(
){
    Column(
        modifier = Modifier.fillMaxWidth(),//.background(Color(0xFF2A43A9)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(LocalCustomColorsPalette.current.defaultMathBasicsTitleBackground)//Color(0xFF143A2F))
        ){

            Card(
                modifier = Modifier
                    .padding(50.dp)
                    .align(Alignment.Center),
                elevation = 0.dp,
                shape = RoundedCornerShape(80.dp),
                backgroundColor = Color(0xFF272727)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vector_icon),
                    contentDescription = "layer icon description",
                    contentScale = ContentScale.Fit, // crop the image if it's not a square
                    modifier = Modifier.size(280.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFE7E7E7))
                )
            }

            /*
            Card(
                modifier = Modifier
                    .padding(50.dp)
                    .align(Alignment.Center),
                elevation = 0.dp,
                shape = RoundedCornerShape(110.dp),
                backgroundColor = Color.Transparent
            ) {
                Image(
                    painter = painterResource(id = R.drawable.electric_charges_icon),
                    contentDescription = "layer icon description",
                    contentScale = ContentScale.Fit, // crop the image if it's not a square
                    modifier = Modifier.size(280.dp)
                )
            }
            */
        }

        /*
        Text(text = "Math Basics",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 10.dp),
            fontFamily = FontFamily.Serif
        )
        */
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun mathbasicsContent(
    appViewModel: AppViewModel,
){
    //val appState by appViewModel.appState.collectAsState()
/*
    Button(onClick = { /***/ }) {
        Text("showRawJson")
    }
    */
    


    val fundamentalCards = buildMathBasicsContent()

    for (card in fundamentalCards){

        /**Text(text = "card.id: ${card.id}, cardOrder: ${card.index}")*/

        VerticalFundamentalCard(
            appViewModel = appViewModel,
            fundamentalCard = card,
            contentComposable = card.contentComposable
        )
        /*
        if (card.content.length < 250 ){
            HorizontalFundamentalsCard(
                appViewModel = appViewModel,
                fundamentalCard = card,
                contentComposable = card.contentComposable
            )
        }else{
            VerticalFundamentalCard(
                appViewModel = appViewModel,
                fundamentalCard = card,
                contentComposable = card.contentComposable
            )
        }
*/
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun AddSectionIcon(
    appViewModel: AppViewModel
){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .clickable {
                //appViewModel.addFundamentalCard()
            },
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.size(90.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.add_card_icon_3), contentDescription = null,
            colorFilter = ColorFilter.tint(LocalCustomColorsPalette.current.addCardIconColor)
        )
    }
    Text("Add card", fontSize = 10.sp)
}






/** The original card*/
@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
private fun HorizontalFundamentalsCard(
    appViewModel: AppViewModel,
    fundamentalCard: FundamentalCard,
    contentComposable: @Composable () -> Unit = {}
){

    val appState by appViewModel.appState.collectAsState()

    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.combinedClickable(
            onClick = { },
            onLongClick = {
                appViewModel.updateShowArrowButtons()
            },
        ),
        backgroundColor = Color.Transparent
    ) {
        Box(
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.weight(60f)
                ) {
                    /*
                    FundamentalCardTitleText(
                        cardId = fundamentalCard.id,
                        text = fundamentalCard.title,
                        appViewModel = appViewModel
                    )
                    */
                    Text(text = "${fundamentalCard.title}", color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 20.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold)


                    Spacer(modifier = Modifier.height(16.dp))

                    /*
                    FundamentalCardContentText(
                        cardId = fundamentalCard.id,
                        text = fundamentalCard.content,
                        appViewModel = appViewModel
                    )
                    */
                    contentComposable()
                    //Text(text = "${fundamentalCard.content}", color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 18.sp, fontFamily = FontFamily.Serif)
                }

                Spacer(modifier = Modifier.width(16.dp))

                val imageid = getDrawableFromFundamentalCard(imageid = fundamentalCard.imageId)
                Image(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(160.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    painter = painterResource(id = imageid),
                    contentDescription = "layer icon description",
                )
            }

            if (appState.showArrowButtons){
                Box(modifier = Modifier.align(Alignment.CenterEnd)){
                    moveCardArrows(
                        appViewModel = appViewModel,
                        card = fundamentalCard
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
private fun VerticalFundamentalCard(
    appViewModel: AppViewModel,
    fundamentalCard: FundamentalCard,
    contentComposable: @Composable () -> Unit = {}
){
    var toUIUpdate by remember{ mutableStateOf(0) }
    val appState by appViewModel.appState.collectAsState()

    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp),
        onClick = { toUIUpdate += 1 },
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

                Text(text = "uiupdate: ${toUIUpdate}")
                Text(text = "${fundamentalCard.title}", color = if (isSystemInDarkTheme()) Color.White else Color.Black, fontSize = 20.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(2.dp)
                    .background(Color(0xFF969696)))
                /*
                FundamentalCardTitleText(
                    cardId = fundamentalCard.id,
                    text = fundamentalCard.title,
                    appViewModel = appViewModel
                )
                */
                Spacer(modifier = Modifier.height(16.dp))

                if (toUIUpdate % 2 != 0){
                    toUIUpdate += 1
                }else{
                    contentComposable()
                }

                /*
                FundamentalCardContentText(
                    cardId = fundamentalCard.id,
                    text = fundamentalCard.content,
                    appViewModel = appViewModel
                )
                */

                Spacer(modifier = Modifier.height(16.dp))

                /*
                val imageid = getDrawableFromFundamentalCard(imageid = fundamentalCard.imageId)
                Image(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(160.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    painter = painterResource(id = imageid),
                    contentDescription = "layer icon description",
                )
                */
            }
            if (appState.showArrowButtons){
                Box(modifier = Modifier.align(Alignment.CenterEnd)){
                    moveCardArrows(
                        appViewModel = appViewModel,
                        card = fundamentalCard
                    )
                }
            }
        }
    }
}