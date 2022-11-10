package com.luna.ui.screens.dashboard.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.luna.ui.screens.dashboard.state.initial.DashboardInitialState
import com.luna.ui.screens.dashboard.viewmodel.DashboardViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@ExperimentalComposeUiApi
@Destination
@Composable
fun DashboardScreen(
    navigator: DestinationsNavigator,
    viewModel : DashboardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = "") {
        viewModel.uiAction.collect { uiAction ->
            when (uiAction) {
                DashboardViewModel.UiAction.ShowDeviceTiltInstruction -> {

                }
                DashboardViewModel.UiAction.ShowFaceRecognitionInstruction -> {

                }
            }
        }
    }


    when (uiState.state) {
        DashboardViewModel.UiState.State.Error -> {
            Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }
        DashboardViewModel.UiState.State.Initial -> {
            DashboardInitialState()
        }
    }
}

