package com.luna.ui.screens.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luna.core.custom_implementations.LunaCountDownTimer
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

    private val countDownTimer = LunaCountDownTimer(
        millisInFuture = 3000,
        countDownInterval = 1000,
        onTimerFinished = { timer ->
            timer.start()
            submitUiState(_uiState.value.copy(enableDeviceTiltInstructionSubmitButton = true))
        }, onTimerTick = {})
    private var currentDashboardInstruction = DashboardInstruction.NoInstruction

    private var deviceTiltInstructionsTimerRunning = false

    init {
        observeUiEvents()
    }


    private fun observeUiEvents() = viewModelScope.launch {
        uiEvent.collect { event ->
            when (event) {
                UiEvent.StartButtonClicked -> {
                    startDeviceTiltInstructions()
                }
                UiEvent.DeviceTiltSubmitButtonClicked -> {
                    startFaceRecognitionInstruction()
                }
                UiEvent.OnFaceRecognized -> {
                    resetScreen()
                }
            }
        }
    }

    private fun startFaceRecognitionInstruction() {
        currentDashboardInstruction = DashboardInstruction.FaceRecognition
        submitAction(UiAction.OpenFaceRecognitionInstruction)
    }

    private fun startDeviceTiltInstructions() {
        getDeviceAngle()
        submitAction(UiAction.OpenDeviceTiltInstruction)
        currentDashboardInstruction = DashboardInstruction.DeviceTilt
    }

    private fun getDeviceAngle() = viewModelScope.launch {
        lunaRepository.getDeviceTiltAngle { angle ->
            submitUiState(_uiState.value.copy(deviceAngle = angle.toString()))
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
                if (currentDashboardInstruction != DashboardInstruction.FaceRecognition) return@getDeviceTiltAngle
                submitAction(UiAction.OpenDeviceTiltInstruction)
                submitUiState(_uiState.value.copy(enableDeviceTiltInstructionSubmitButton = false))
            }
        }
    }

    private fun resetScreen() {
        submitAction(UiAction.ShowSuccessfulFaceRecognitionToast)
        countDownTimer.cancel()
        deviceTiltInstructionsTimerRunning = false
        currentDashboardInstruction = DashboardInstruction.NoInstruction
        submitUiState(UiState())
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
        object StartButtonClicked : UiEvent
        object DeviceTiltSubmitButtonClicked : UiEvent
        object OnFaceRecognized : UiEvent
    }

    data class UiState(
        val state: State = State.Initial,
        val isDeviceInRange: Boolean = false,
        val enableDeviceTiltInstructionSubmitButton: Boolean = false,
        val deviceAngle: String = "0",
        var errorMessage: String = ""
    ) {
        enum class State {
            Error,
            Initial,
        }
    }

    sealed interface UiAction {
        object NoAction : UiAction
        object OpenDeviceTiltInstruction : UiAction
        object OpenFaceRecognitionInstruction : UiAction
        object ShowSuccessfulFaceRecognitionToast : UiAction
    }
}