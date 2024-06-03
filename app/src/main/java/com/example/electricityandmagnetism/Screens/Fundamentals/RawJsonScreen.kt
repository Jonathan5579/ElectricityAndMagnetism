package com.example.electricityandmagnetism.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.electricityandmagnetism.AppViewModel
import com.example.electricityandmagnetism.DEFAULT_RAW_JSON
import com.example.electricityandmagnetism.DataStore.DataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RawJsonScreen(
    appViewModel: AppViewModel,
    dataStore: DataStore
){

    val appState by appViewModel.appState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                //val stringToSave = appViewModel.collectCardText()
                //val stringToSave = appState.textFieldVal
                dataStore.saveToken(appState.rawJson)
                appViewModel.showHideRawJson()
            }
        }) {
            Text("Save")
        }
        TextField(
            value = appState.rawJson,
            onValueChange = {
                appViewModel.updateRawJson(it)
            }
        )
        Spacer(Modifier.height(270.dp))
    }
}