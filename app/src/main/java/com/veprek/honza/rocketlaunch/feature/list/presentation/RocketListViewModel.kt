package com.veprek.honza.rocketlaunch.feature.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veprek.honza.rocketlaunch.repository.RocketRepository
import com.veprek.honza.rocketlaunch.repository.entity.ResponseWrapper
import com.veprek.honza.rocketlaunch.repository.model.Rocket
import com.veprek.honza.rocketlaunch.repository.model.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RocketListViewModel(private val rocketRepository: RocketRepository) : ViewModel() {
    private val _rockets = MutableStateFlow(ResponseWrapper<List<Rocket>?>(State.LOADING, null))
    val rockets: StateFlow<ResponseWrapper<List<Rocket>?>> get() = _rockets
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing.asStateFlow()

    init {
        getRockets()
    }

    fun refresh() {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            rocketRepository.getAllRockets().collect { response ->
                _isRefreshing.emit(false)
                _rockets.value = response
            }
        }
    }
}
