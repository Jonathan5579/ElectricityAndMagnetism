package com.example.electricityandmagnetism.Screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.Drawer.Drawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.electricityandmagnetism.R
import com.example.electricityandmagnetism.Screens.Fundamentals.Fundamentals
import com.example.electricityandmagnetism.TopBarBackgroundColor
import com.example.electricityandmagnetism.TopBarTextColor


enum class AppScreens(@StringRes val title: Int) {
    Home(title = R.string.home),
    Configuration(title = R.string.configuration),
    SelectTopic(title = R.string.select_topic),
    Fundamentals(title = R.string.fundamentals)
}


@Composable
fun appTopBar(
    currentScreen: AppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    openDrawer: () -> Unit,
    //openHelpMenu: () -> Unit
){
    R.string.home
    TopAppBar(
        backgroundColor = Color(TopBarBackgroundColor),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(currentScreen.title), color = Color(TopBarTextColor))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    /*if( currentScreen in SCREENS_WITH_AID){
                        IconButton(onClick = openHelpMenu) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.help_icon_24),
                                contentDescription = stringResource(id = R.string.wip_home_card_image_description),//"Bus home image", ,
                                //contentScale = ContentScale.FillBounds
                            )
                            /*
                            Icon(
                                tint = Color.White,
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(R.string.back_button_description)
                            )

                             */
                        }
                    }
                    */
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

@Composable
fun ElectricityAndMagnetismApp(
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
            appTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null, //can only navigate back if theres back
                navigateUp = { navController.navigateUp() },
                openDrawer = { openDrawer() }
                //openHelpMenu = { appViewModel.updateMenuCardState() }
            )
        },
    ) { padding -> //esto es solo porque Scaffold me obliga a usar un padding, si lo pones donde sea en principio no hace alguna acción, ambién puedes no usarlo pero te deja una linea roja fea en el código

        val VariableToUnseeTheErrorOfPadding = padding
        Surface(
            color = MaterialTheme.colors.background
            //color = Color(0xFFFFFFFF)
        ) {

            DrawerAndNavigation(
                appViewModel = appViewModel,
                navController = navController,
                currentScreen = currentScreen,
                drawerState = drawerState,
                scope = scope,
            )
        }
    }
}


@Composable
fun DrawerAndNavigation(
    appViewModel: AppViewModel,
    navController: NavHostController,
    currentScreen: AppScreens,
    drawerState: DrawerState,
    scope: CoroutineScope,
){/*
    //Navigation variables
    val navController = rememberNavController()

    //backstack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RfIdScreens.valueOf(
        backStackEntry?.destination?.route ?: RfIdScreens.Home.name
    )
    //Drawer variables
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }
    */
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
        NavHost(
            navController = navController,
            startDestination = AppScreens.Home.name,
        ) {

            composable(route = AppScreens.Home.name){
                HomeScreen(
                    appViewModel = appViewModel,
                    onHomeCardClicked = {
                        navController.navigate(AppScreens.SelectTopic.name)
                        //appViewModel.retreiveFormatsData()
                        //appViewModel.matchRFIDCurrentConfiguration()
                    }
                )
            }

            composable(route = AppScreens.SelectTopic.name){
                SelectTopicScreen(
                    appViewModel = appViewModel,
                    navigateNextScreen = {
                        navController.navigate(AppScreens.Fundamentals.name)
                    }
                )
            }

            composable(route = AppScreens.Fundamentals.name){
                Fundamentals(
                    appViewModel = appViewModel,
                    navigateNextScreen = {
                    }
                )
            }

            /*
            composable(route = AppScreens.Configuration.name){
                ConfigurationScreen(
                    appViewModel = appViewModel,
                    //onNextButtonClicked = { navController.navigate(RfIdScreens.NavigationMap.name) }
                )
            }


            composable(route = AppScreens.SelectLevel.name){
                selectLayerScreen(appViewModel = appViewModel,
                    navigateNextScreen = {
                        //appViewModel.initQuestions()
                        navController.navigate(AppScreens.StartAuditScreen.name)
                    }
                )
                /*
                NavigationMapScreen(
                    navigateReadAndWrite = {
                        navController.navigate(RfIdScreens.ReadAndWrite.name)
                    },
                    navigateGetEPCFromContainer = {
                        navController.navigate(RfIdScreens.GetEPCFromContainer.name)
                        appViewModel.setGetEPCFromContainerSettings("GetEPCFromContainer")
                    },
                    navigateGetEpcsFromSerialNumber = {
                        navController.navigate(RfIdScreens.GetEpcsFromSerialNumber.name)
                        appViewModel.setGetEpcsFromSerialNumberSettings("GetEpcsFromSerialNumber")

                    },
                    navigateGetInfoFromEPC = {
                        navController.navigate(RfIdScreens.GetInfoFromEPC.name)
                        appViewModel.setGetInfoFromEPCSettings("GetInfoFromEPC")
                    },
                    navigateWriteTIDIntoEPC = {
                        navController.navigate(RfIdScreens.WriteTIDIntoEPC.name)
                        appViewModel.setWriteTIDintoEPCSettings("WriteTIDIntoEPC")
                    }
                )
                */
            }
            */
        }
    }
}