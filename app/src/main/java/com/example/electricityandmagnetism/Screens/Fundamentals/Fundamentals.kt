package com.example.electricityandmagnetism.Screens.Fundamentals

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.*
import com.example.electricityandmagnetism.DataStore.DataStore
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.Screens.RawJsonScreen
import com.example.electricityandmagnetism.ui.theme.LocalCustomColorsPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Fundamentals(
    dataStore: DataStore,
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit
) {

    val appState by appViewModel.appState.collectAsState()

    if (appState.showRawJson){
        RawJsonScreen(
            appViewModel = appViewModel,
            dataStore = dataStore
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FundamentalsTitle()

        FundamentalsContent(
            appViewModel = appViewModel
        )

        Spacer(modifier = Modifier.height(50.dp))

        AddSectionIcon(appViewModel)

        Spacer(modifier = Modifier.height(80.dp))
    }

    SaveContentFloatingButton(
        appViewModel = appViewModel,
        dataStore = dataStore
    )
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
fun SaveContentFloatingButton(
    appViewModel: AppViewModel,
    dataStore: DataStore
){

    val appState by appViewModel.appState.collectAsState()

    if (appState.anyTextHasChanged){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
        ){
            Box(modifier = Modifier
                .background(Color(0xFF000000), shape = CircleShape)
                .size(height = 50.dp, width = 150.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    CoroutineScope(Dispatchers.IO).launch {
                        val stringToSave = appViewModel.collectCardText()

                        dataStore.saveToken(stringToSave)
                        appViewModel.resetanyTextHasChanged()
                    }
                },
                contentAlignment = Alignment.Center
            ){
                Row(modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
                    , verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_save_24),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Text("Save Content", fontSize = 14.sp, color = Color.White,
                        modifier = Modifier.padding(start = 10.dp))
                }
            }
        }
    }
}



@Composable
fun FundamentalsTitle(
){
    Column(
        modifier = Modifier.fillMaxWidth(),//.background(Color(0xFF2A43A9)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF485DB1))
        ){
            Card(
                modifier = Modifier
                    .padding(50.dp)
                    .align(Alignment.Center),
                elevation = 0.dp,
                shape = RoundedCornerShape(110.dp),
                backgroundColor = Color(0xFF2A43A9)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.electric_charges_icon),
                    contentDescription = "layer icon description",
                    contentScale = ContentScale.Fit, // crop the image if it's not a square
                    modifier = Modifier.size(280.dp)
                )
            }
        }


        Text(text = "FUNDAMENTALS",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 10.dp),
            fontFamily = FontFamily.Serif
        )
    }
}







@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FundamentalsContent(
    appViewModel: AppViewModel,
){
    val appState by appViewModel.appState.collectAsState()

    Button(onClick = { appViewModel.showHideRawJson() }) {
        Text("showRawJson")
    }

    /*
    Button(onClick = { appViewModel.updateShowArrowButtons() }) {
        Text("updateShowArrowButtons")
    }
    */

    for (card in appState.fundamentalCards){

        Text(text = "card.id: ${card.id}, cardOrder: ${card.index}")
/*
        GeneralFundamentalsCard(
            appViewModel = appViewModel,
            fundamentalCard = card,
            showContentVertically = card.content.length > 250
        )
        */


        if (card.content.length < 250 ){
            HorizontalFundamentalsCard(
                appViewModel = appViewModel,
                fundamentalCard = card
            )
        }else{
            VerticalFundamentalCard(
                appViewModel = appViewModel,
                fundamentalCard = card
            )
        }



        /*
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD5D5D5)),
            contentAlignment = Alignment.CenterEnd
        ){
            itemOrderingTextField(
                appViewModel = appViewModel,
                card = card
            )
        }
        */

        Spacer(modifier = Modifier.height(20.dp))
    }
}
/*
@Composable
fun itemOrderingTextField(
    appViewModel: AppViewModel,
    card: FundamentalCard,
){
    Row(

    ){
        TextField(
            modifier = Modifier.size(50.dp),
            value = "${card.index}",
            onValueChange = {
                appViewModel.updateFundamentalItemOrdering(
                    cardId = card.id,
                    itemOrdering = it
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            textStyle = TextStyle().copy(fontSize = 12.sp, color = Color.Black),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xA000000)
            )
        )

        Column(){
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        appViewModel.moveCardUp(cardIndex = card.index)
                    },
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        appViewModel.moveCardDown(cardIndex = card.index)
                    },
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }


    }

}
*/

@Composable
fun moveCardArrows(
    appViewModel: AppViewModel,
    card: FundamentalCard,
){


    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(50))
            .background(Color(0xFFD9E9DB)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    appViewModel.moveCardUp(cardIndex = card.index)
                },
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = null
        )
        Text(text = "${card.index}", fontSize = 16.sp)
        Icon(
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    appViewModel.moveCardDown(cardIndex = card.index)
                },
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null
        )
        Icon(
            modifier = Modifier
                .size(30.dp)
                .clickable { appViewModel.removeCard(card.index) },
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color(0xFF471515)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
private fun VerticalFundamentalCard(
    appViewModel: AppViewModel,
    fundamentalCard: FundamentalCard,
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FundamentalCardTitleText(
                    cardId = fundamentalCard.id,
                    text = fundamentalCard.title,
                    appViewModel = appViewModel
                )

                Spacer(modifier = Modifier.height(16.dp))

                FundamentalCardContentText(
                    cardId = fundamentalCard.id,
                    text = fundamentalCard.content,
                    appViewModel = appViewModel
                )

                Spacer(modifier = Modifier.height(16.dp))

                val imageid = getDrawableFromFundamentalCard(imageid = fundamentalCard.imageId)
                Image(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(160.dp)
                        .clip(RoundedCornerShape(32.dp)),
                    painter = painterResource(id = imageid),
                    contentDescription = "layer icon description",
                )
                /*
                Card(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(26.dp),
                    modifier = Modifier.padding( 10.dp)
                ) {
                    Image(
                        painter = painterResource(id = imageid),
                        contentDescription = "layer icon description",
                        contentScale = ContentScale.FillBounds, // crop the image if it's not a square
                        modifier = Modifier
                            .size(150.dp)
                            .weight(40f)
                    )
                }
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

/** The original card*/
@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
private fun HorizontalFundamentalsCard(
    appViewModel: AppViewModel,
    fundamentalCard: FundamentalCard,
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
                    FundamentalCardTitleText(
                        cardId = fundamentalCard.id,
                        text = fundamentalCard.title,
                        appViewModel = appViewModel
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FundamentalCardContentText(
                        cardId = fundamentalCard.id,
                        text = fundamentalCard.content,
                        appViewModel = appViewModel
                    )
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
                /*
                Card(
                    modifier = Modifier.weight(40f),
                    elevation = 0.dp,
                    //shape = RoundedCornerShape(32.dp),
                ) {
                    Image(
                        modifier = Modifier.size(150.dp).clip(RoundedCornerShape(32.dp)),
                        painter = painterResource(id = imageid),
                        contentDescription = "layer icon description",
                    )
                }*/
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


/*
@ExperimentalMaterialApi
@Composable
fun GeneralFundamentalsCard(
    appViewModel: AppViewModel,
    fundamentalCard: FundamentalCard,
    showContentVertically: Boolean
){

    val content: @Composable () -> Unit = {
        Column(
            //modifier = Modifier.weight(60f)
        ) {
            FundamentalCardTitleText(
                cardId = fundamentalCard.id,
                text = fundamentalCard.title,
                appViewModel = appViewModel
            )

            FundamentalCardContentText(
                cardId = fundamentalCard.id,
                text = fundamentalCard.content,
                appViewModel = appViewModel
            )
        }

        val imageid = getDrawableFromFundamentalCard(imageid = fundamentalCard.imageId)
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier.padding( 16.dp)
        ) {
            Image(
                painter = painterResource(id = imageid),
                contentDescription = "layer icon description",
                contentScale = ContentScale.FillBounds, // crop the image if it's not a square
                modifier = Modifier
                    .size(150.dp)
            )
        }
    }


    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(5.dp),
        onClick = { }
    ) {
        if (showContentVertically){
            generalVertical { content() }
        }
        else{
            generalHorizontal { content() }
        }
    }
}

@Composable
fun generalHorizontal(
    content: @Composable () -> Unit = {},
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 0.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }

}

@Composable
fun generalVertical(
    content: @Composable () -> Unit = {},
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 0.dp, top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}
*/

@Composable
fun FundamentalCardTitleText(
    text: String,
    cardId: Int,
    appViewModel: AppViewModel
){

    BasicTextField(
        value = text,
        onValueChange = {
            appViewModel.updateFundamentalsTitleTextField(
                cardId = cardId,
                title = it
            )
        },
        textStyle = TextStyle.Default.copy(
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif
        ),
    )

    /*
    OutlinedTextField(
        value = text,
        onValueChange = {
            appViewModel.updateFundamentalsTitleTextField(
                cardId = cardId,
                title = it
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.surface,//MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity),,
            focusedIndicatorColor = Color(0xFF224997),
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif
        ),
    )
    */
}

@Composable
fun FundamentalCardContentText(
    text: String,
    cardId: Int,
    appViewModel: AppViewModel
){
    BasicTextField(
        value = text,
        onValueChange = {
            appViewModel.updateFundamentalsTextField(
                cardId = cardId,
                textfieldText = it
            )
        },
        textStyle = TextStyle.Default.copy(
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif
        ),
    )
/*
    OutlinedTextField(
        modifier = Modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        value = text,
        onValueChange = {
            appViewModel.updateFundamentalsTextField(
                cardId = cardId,
                textfieldText = it
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.surface,//MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity),,
            focusedIndicatorColor = Color(0xFF224997),
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif
        ),

    )
 */
}


@Composable
private fun AddSectionIcon(
    appViewModel: AppViewModel
){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .clickable {
                appViewModel.addFundamentalCard()
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



/*
/**Funciones de apoyo más que de despliegue de UI*/
@Composable
fun getDrawableFromFundamentalCard(
    imageid: Int
): Int{
    return when (imageid){
        RDrawableElectricFillerImage -> R.drawable.electric_filler_image
        RDrawableElectricCharges -> R.drawable.electric_charges_icon
        RDrawableElectricField -> R.drawable.electric_field_icon
        else -> R.drawable.imageplaceholder
    }
}
*/
@Composable
fun HorizontalBreak(
    height: Dp = 2.dp,
    TopPadding: Dp = 50.dp,
    BottomPadding: Dp = 50.dp
){

    Spacer(modifier = Modifier.height(TopPadding))

    Spacer(modifier = Modifier
        .height(height)
        .fillMaxWidth()
        .background(Color.Black)
    )

    Spacer(modifier = Modifier.height(BottomPadding))
}