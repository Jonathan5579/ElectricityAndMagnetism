package com.example.electricityandmagnetism.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.DataStore.DataStore
import com.example.electricityandmagnetism.Drawer.Drawer
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.Screens.Fundamentals.Fundamentals
import com.example.electricityandmagnetism.Screens.Griffiths.MathBasicsScreen
import com.example.electricityandmagnetism.TopBarTextColor
import com.example.electricityandmagnetism.ui.theme.LocalCustomColorsPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun appTopBar2(
    currentScreen: AppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    openDrawer: () -> Unit,
){
    if (currentScreen == AppScreens.Griffiths) {
        TopAppBar(
            backgroundColor = Color(0xFF1A1A1A),//Color(TopBarBackgroundColor),
            title = {},
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_description)
                    )
                }
            },
            elevation = 0.dp

        )
        return
    }


    TopAppBar(
        backgroundColor = LocalCustomColorsPalette.current.topBarBackgroundColor,//Color(TopBarBackgroundColor),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(currentScreen.title), color = Color(TopBarTextColor))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                }
            }

        },
        navigationIcon = {
            if (canNavigateBack && currentScreen != AppScreens.Configuration/*stringResource(currentScreen.title) != stringResource(id = R.string.configuration)*/) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_description)
                    )
                }
            }
            else{
                IconButton(onClick = openDrawer ){
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(id = R.string.menu_icon_description)//"Menu Description"
                    )
                }
            }
        }
    )
}

/**I think what I tried to do with this change of screen manager is to remove the ugly "DrawerAndNavigation function and just
 * put all in one place.*/
@Composable
fun ElectricityAndMagnetismApp2(
    dataStore: DataStore,
    appViewModel: AppViewModel = viewModel()
){
    //Navigation variables
    val navController = rememberNavController()

    //backstack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreens.valueOf(
        backStackEntry?.destination?.route ?: AppScreens.Home.name
    )


    //Drawer variables
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }

    Scaffold(
        topBar = {
            appTopBar2(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null, //can only navigate back if theres back
                navigateUp = { navController.navigateUp() },
                openDrawer = { openDrawer() }
            )
        },
    ){ padding -> //esto es solo porque Scaffold me obliga a usar un padding, si lo pones donde sea en principio no hace alguna acción, ambién puedes no usarlo pero te deja una linea roja fea en el código
        val VariableToUnseeTheErrorOfPadding = padding
        NavHost(
            navController = navController,
            startDestination = AppScreens.Home.name,
        ) {
            composable(route = AppScreens.Home.name){

                ModalDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = drawerState.isOpen,
                    drawerContent = {
                        Drawer(
                            onDestinationClicked = { route ->
                                scope.launch { drawerState.close() }
                                navController.navigate(route) {
                                    //popUpTo(navController.graph.startDestinationId)
                                    popUpTo = navController.graph.startDestinationId
                                    launchSingleTop = true
                                }
                            },
                            currentScreen = currentScreen
                        )
                    }
                ){
                    HomeScreen(
                        appViewModel = appViewModel,
                        onHomeCardClicked = {
                            navController.navigate(AppScreens.SelectTopic.name)
                        }
                    )
                }
            }

            composable(route = AppScreens.SelectTopic.name){
                SelectTopicScreen(
                    appViewModel = appViewModel,
                    navigateFundamentalsScreen = { navController.navigate(AppScreens.Fundamentals.name) },
                    navigateGriffithsScreen = { navController.navigate(AppScreens.Griffiths.name) },
                    navigateElectrostaticScreen = { navController.navigate(AppScreens.Electrostatics.name)}
                )
            }

            composable(route = AppScreens.Fundamentals.name){

                Fundamentals(
                    dataStore = dataStore,
                    appViewModel = appViewModel,
                    navigateNextScreen = {
                    }
                )
            }

            composable(route = AppScreens.Griffiths.name){
                MathBasicsScreen()
            }
        }
    }


}


@Composable
private fun customAppTopBar(
    navigateUp: () -> Unit,
){
    TopAppBar(
        backgroundColor = Color(0xFF7C9486),
        title = {  },
        navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_description)
                    )
                }
        }
    )
}