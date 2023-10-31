package com.example.electricityandmagnetism.Screens.Fundamentals

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.*
import com.example.electricityandmagnetism.DataStore.DataStore
import com.example.electricityandmagnetism.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Fundamentals(
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit
) {

    val appState by appViewModel.appState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(0.dp))

        FundamentalsTitleAndContent(
            navigateNextScreen = navigateNextScreen,
            appViewModel = appViewModel
        )
        /*
        FundamentalsColumn(
            navigateNextScreen = navigateNextScreen,
            appViewModel = appViewModel)
        */

        Spacer(modifier = Modifier.height(40.dp))
    }
    if (appState.anyTextHasChanged){
        val dataStore = DataStore(LocalContext.current)

        Box(modifier = Modifier.fillMaxSize()
            .padding(20.dp)
        ){
            Box(modifier = Modifier
                .background(Color(0xFF000000), shape = CircleShape)
                .size(height = 50.dp, width = 150.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    CoroutineScope(Dispatchers.IO).launch {
                        val stringToSave = appState.textFieldVal
                        dataStore.saveToken(stringToSave)
                        appViewModel.resetanyTextHasChanged()
                    }
                },
                contentAlignment = Alignment.Center
            ){
                Row(modifier = Modifier.align(Alignment.Center)
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FundamentalsTitleAndContent(
    appViewModel: AppViewModel,
    navigateNextScreen: () -> Unit
){
    val appState by appViewModel.appState.collectAsState()
    FundamentalsTitle()

    updatedSavedValue(
        context = LocalContext.current,
        appViewModel = appViewModel
    )

    for (card in appState.fundamentalCards){
        if (card.id == 2) HorizontalBreak()

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

    }
}

@Composable
fun updatedSavedValue(
    context: Context,
    appViewModel: AppViewModel
){
    val appState by appViewModel.appState.collectAsState()
    val tokenValue = appState.textFieldVal

    val dataStore = DataStore(context)
    val tokenText = dataStore.getAccessToken.collectAsState(initial = "")

    TextField(
        value = tokenValue,
        onValueChange = {
            appViewModel.updateTextFieldValue(it)
        },
    )
    Text(text = "value saved in datastore: ${tokenText.value}")

    Button(
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val stringToSave = appState.textFieldVal
                dataStore.saveToken(stringToSave)
            }
        }
    ) {
        Text(text = "Update Token")
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
                //border = BorderStroke(width = ),
                shape = RoundedCornerShape(110.dp),
                backgroundColor = Color(0xFF2A43A9)

                ) {
                Image(
                    painter = painterResource(id = R.drawable.electric_charges),
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




@ExperimentalMaterialApi
@Composable
fun VerticalFundamentalCard(
    appViewModel: AppViewModel,
    fundamentalCard: FundamentalCard,
){
    Card(
        //modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
        modifier = Modifier.padding(vertical = 15.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(5.dp),
        onClick = {
        }
    ) {
        Box(
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    //.background(Color(0xFFFFFFFF))
                    .padding(start = 5.dp, end = 0.dp, top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = fundamentalCard.title,
                    modifier = Modifier.padding(start = 15.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    //color = Color(textTitleColor),
                    fontFamily = FontFamily.Serif
                )

                FundamentalCardContentText(
                    cardId = fundamentalCard.id,
                    text = fundamentalCard.content,
                    appViewModel = appViewModel
                )

                val imageid = getDrawableFromFundamentalCard(imageid = fundamentalCard.imageId)
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
            }
        }
    }
}

/** The original card*/
@ExperimentalMaterialApi
@Composable
fun HorizontalFundamentalsCard(
    appViewModel: AppViewModel,
    fundamentalCard: FundamentalCard,
){
    Card(
        //modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
        modifier = Modifier.padding(vertical = 15.dp),
        elevation = 1.dp,
        shape = RoundedCornerShape(5.dp),
        onClick = {
        }
    ) {
        Box(
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    //.background(Color(0xFFFFFFFF))
                    .padding(start = 5.dp, end = 0.dp, top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(60f)
                    ) {
                        Text(text = fundamentalCard.title,
                            modifier = Modifier.padding(start = 15.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            //color = Color(textTitleColor),
                            fontFamily = FontFamily.Serif
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
                }
            }
        }
    }
}



@Composable
fun FundamentalCardContentText(
    text: String,
    cardId: Int,
    appViewModel: AppViewModel
){
    OutlinedTextField(
        value = text,
        onValueChange = {
            appViewModel.updateFundamentalsTextField(
                cardId = cardId,
                textfieldText = it
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            //textColor = Color.Black,
            disabledTextColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.surface,//MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity),,
            focusedIndicatorColor = Color(0xFF224997),
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 18.sp,
            //textAlign = TextAlign.Justify,
            //color = Color(textSubTitleColor),
            fontFamily = FontFamily.Serif
        ),

    )
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