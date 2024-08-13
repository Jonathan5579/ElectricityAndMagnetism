package com.example.electricityandmagnetism

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.electricityandmagnetism.DataStore.DataStore
import com.example.electricityandmagnetism.Screens.ElectricityAndMagnetismApp
import com.example.electricityandmagnetism.Screens.ElectricityAndMagnetismApp2
import com.example.electricityandmagnetism.ui.theme.ElectricityAndMagnetismTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appViewModel: AppViewModel by viewModels()
            ElectricityAndMagnetismTheme {
                // A surface container using the 'background' color from the theme
                SetStatusBarColor(
                    Color(0xFF000000)
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colors.background
                ) {

                    /**Esto va aquí y no dentro de una función de appviewmodel porque el collecastate
                     * necesita ser llamado desde un composable*/
                    val dataStore = DataStore(LocalContext.current)
                    val dataStoreJson = dataStore.getAccessToken.collectAsState(initial = "")
                    Log.i("CollectText", "Stored Json: ${dataStoreJson.value}")
                    appViewModel.buildCardElementsFromSavedDataStore(dataStoreJson.value)
                    ElectricityAndMagnetismApp2(
                        dataStore = dataStore,
                        appViewModel = appViewModel
                    )
                }
            }
        }
    }
}



@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}