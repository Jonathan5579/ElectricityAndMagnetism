package com.example.electricityandmagnetism

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppUiState(

    /**Select area and layer screen*/
    val selectedFormatID: Int = 0,
    /***/
)

class AppViewModel: ViewModel() {

    private val _appState = MutableStateFlow(AppUiState())
    val appState: StateFlow<AppUiState> = _appState.asStateFlow()
}