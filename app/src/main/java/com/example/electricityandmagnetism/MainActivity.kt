package com.example.electricityandmagnetism

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.electricityandmagnetism.Screens.ElectricityAndMagnetismApp
import com.example.electricityandmagnetism.ui.theme.ElectricityAndMagnetismTheme

class MainActivity : ComponentActivity() {


    private val appViewModel: AppViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElectricityAndMagnetismTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ElectricityAndMagnetismApp(appViewModel)
                }
            }
        }
    }
}