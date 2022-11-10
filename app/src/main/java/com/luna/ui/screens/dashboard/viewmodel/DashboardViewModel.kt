package com.luna.ui.screens.dashboard.viewmodel

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luna.data.repository.LunaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val lunaRepository: LunaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<UiAction>()
    val uiAction = _uiAction.asSharedFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    private val uiEvent = _uiEvent.asSharedFlow()

    private val countDownTimer = object : CountDownTimer(3000, 1000) {
        override fun onTick(p0: Long) {
            Log.d("defaultAppDebuger", "deviceInVerticalRange: ${_uiState.value.isDeviceInRange}")
        }

        override fun onFinish() {
            deviceTiltInstructionsTimerRunning = false
            // TODO - go to 2nd step
            restartTimer()
        }
    }

    private var deviceTiltInstructionsTimerRunning = false

    init {
        observeUiEvents()
        getDeviceAngel()
    }

    private fun getDeviceAngel() = viewModelScope.launch {
        lunaRepository.getDeviceTiltAngle { angle ->
            val isDeviceInVerticalRange = angle in 90 downTo 70
            submitUiState(
                _uiState.value.copy(
                    isDeviceInRange = isDeviceInVerticalRange
                )
            )
            if (deviceTiltInstructionsTimerRunning.not() && isDeviceInVerticalRange) {
                countDownTimer.start()
                deviceTiltInstructionsTimerRunning = true
            } else if (deviceTiltInstructionsTimerRunning && isDeviceInVerticalRange.not()) {
                countDownTimer.cancel()
                deviceTiltInstructionsTimerRunning = false
            }
        }
    }

    private fun observeUiEvents() = viewModelScope.launch {
        uiEvent.collect { event ->
            when (event) {
                UiEvent.DeviceTiltButtonClicked -> {

                }
                UiEvent.FaceRecognitionButtonClicked -> {

                }
            }
        }
    }


    private fun submitAction(uiAction: UiAction) = viewModelScope.launch {
        _uiAction.emit(uiAction)
    }

    private fun submitUiState(uiState: UiState) {
        _uiState.update { uiState }
    }

    fun submitEvent(uiEvent: UiEvent) = viewModelScope.launch {
        _uiEvent.emit(uiEvent)
    }

    sealed interface UiEvent {
        object DeviceTiltButtonClicked : UiEvent
        object FaceRecognitionButtonClicked : UiEvent
    }

    data class UiState(
        val state: State = State.Initial,
        val isDeviceInRange: Boolean = false,
        var errorMessage: String = ""
    ) {
        enum class State {
            Error,
            Initial
        }
    }

    sealed interface UiAction {
        object ShowDeviceTiltInstruction : UiAction
        object ShowFaceRecognitionInstruction : UiAction
    }
}