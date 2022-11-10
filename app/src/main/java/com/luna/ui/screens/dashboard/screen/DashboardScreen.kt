package com.luna.ui.screens.dashboard.screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.luna.ui.screens.dashboard.state.initial.DashboardInitialState
import com.luna.ui.screens.dashboard.state.initial.PhoneTiltInstructions
import com.luna.ui.screens.dashboard.viewmodel.DashboardViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@ExperimentalComposeUiApi
@Destination
@Composable
fun DashboardScreen(
    viewModel : DashboardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val uiAction = viewModel.uiAction.collectAsState(initial = DashboardViewModel.UiAction.NoAction)
    when(uiAction.value) {
        DashboardViewModel.UiAction.NoAction -> Unit
        DashboardViewModel.UiAction.ShowDeviceTiltInstruction -> {
            PhoneTiltInstructions(uiState.deviceAngle, uiState.enableDeviceTiltInstructionSubmitButton)
        }
        DashboardViewModel.UiAction.ShowFaceRecognitionInstruction -> {

        }
    }


    when (uiState.state) {
        DashboardViewModel.UiState.State.Error -> {
            Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT).show()
        }
        DashboardViewModel.UiState.State.Initial -> {
            DashboardInitialState(onStartButtonClicked = {
                viewModel.submitEvent(DashboardViewModel.UiEvent.StartButtonClicked)
            })

        }
    }
}

