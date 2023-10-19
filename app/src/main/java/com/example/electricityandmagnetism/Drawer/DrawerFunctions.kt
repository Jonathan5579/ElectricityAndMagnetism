package com.example.electricityandmagnetism.Drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricityandmagnetism.Screens.AppScreens
import com.example.electricityandmagnetism.R


class DrawerScreenVariables(
    screenn: AppScreens,
    iconn: ImageVector,
    iconDescriptionn: Int
){
    var screen: AppScreens
    var icon: ImageVector
    var iconDescription: Int

    init{
        screen = screenn
        icon = iconn
        iconDescription = iconDescriptionn
    }
}

private val drawerScreens = listOf(//screen name, icon, description
    DrawerScreenVariables( AppScreens.Home, Icons.Default.Home, R.string.home_icon_description ),
    DrawerScreenVariables( AppScreens.Configuration, Icons.Default.Settings, R.string.home_icon_description ),
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit,
    currentScreen: AppScreens
) {
    Column(modifier = Modifier
        .background(color = Color(0xFFFEC928))
        .padding(start = 30.dp, top = 30.dp, bottom = 20.dp)
        .fillMaxWidth()
    ) {

        /**
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_round),
            contentDescription = stringResource(id = R.string.siix_icon_description),
            //contentScale = ContentScale.Crop, // crop the image if it's not a square
            modifier = Modifier
                .padding(start = 10.dp)
                .size(80.dp)
            //.clip(CircleShape) // clip to the circle shape
        )
        */

        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.drawer_title),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = stringResource(id = R.string.department),
            fontSize = 13.sp,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
    }
    Column(
        modifier
            .padding(start = 24.dp, top = 20.dp)
            .fillMaxWidth(0.7f)
    ) {

        drawerScreens.forEach { screen ->

            Spacer(Modifier.height(10.dp))
            if ( stringResource(id = currentScreen.title) == stringResource(id = screen.screen.title)){
                drawerOptionYellow(
                    onDestinationClicked = onDestinationClicked,
                    screenNameText = stringResource(id = screen.screen.title),
                    screenNameNavigation = screen.screen.name,
                    imageVector = screen.icon,
                    imageDescription = stringResource(id = screen.iconDescription),
                )
            } else{
                drawerOption(
                    onDestinationClicked = onDestinationClicked,
                    screenNameText = stringResource(id = screen.screen.title),
                    screenNameNavigation = screen.screen.name,
                    imageVector = screen.icon,
                    imageDescription = stringResource(id = screen.iconDescription),
                )
            }
        }

        /* Para mostrar en el drawer atajos para todas las pantallas
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.name,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable { onDestinationClicked(screen.name) }
            )
        }
        */
    }
}


@Composable
fun drawerOptionYellow(
    onDestinationClicked: (route: String) -> Unit,
    imageVector: ImageVector,
    imageDescription: String,
    screenNameText: String,
    screenNameNavigation: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(end = 24.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable { onDestinationClicked(screenNameNavigation) }
            .background(Color(0x34FFE38E))
            .padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            tint = Color(0xDDFFBF00),
            imageVector = imageVector,
            contentDescription = imageDescription
        )
        Text(
            text = screenNameText,
            color = Color(0xDDFFBF00),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}



@Composable
fun drawerOption(
    onDestinationClicked: (route: String) -> Unit,
    imageVector: ImageVector,
    imageDescription: String,
    screenNameText: String,
    screenNameNavigation: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(end = 24.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable { onDestinationClicked(screenNameNavigation) }
            .padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = imageDescription
        )
        Text(
            text = screenNameText,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}

