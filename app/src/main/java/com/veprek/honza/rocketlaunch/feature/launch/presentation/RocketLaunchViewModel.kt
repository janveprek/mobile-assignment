package com.veprek.honza.rocketlaunch.feature.launch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veprek.honza.rocketlaunch.repository.model.RocketState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import quanti.com.kotlinlog.Log

class RocketLaunchViewModel : ViewModel() {
    private val _rocketState = MutableStateFlow(RocketState.WAITING)
    val rocketState: StateFlow<RocketState> get() = _rocketState.asStateFlow()

    private val _isLaunched = MutableStateFlow(true)
    val isLaunched: StateFlow<Boolean> get() = _isLaunched.asStateFlow()

    fun launch() {
        val current = _isLaunched.value
        viewModelScope.launch {
            _rocketState.emit(RocketState.LAUNCHED)
            Log.d("Rocket state: ${_rocketState.value}")
            _isLaunched.emit(!current)
        }
    }

    fun fail() {
        val current = _isLaunched.value
        viewModelScope.launch {
            _rocketState.emit(RocketState.FAILED)
            Log.d("Rocket state: ${_rocketState.value}")
            _isLaunched.emit(!current)
        }
    }

    fun failInAir() {
        viewModelScope.launch {
            _rocketState.emit(RocketState.FAILED)
            Log.d("Rocket state: ${_rocketState.value}")
        }
    }
}
