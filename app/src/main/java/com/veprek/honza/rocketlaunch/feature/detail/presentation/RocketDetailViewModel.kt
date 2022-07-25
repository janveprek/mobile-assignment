package com.veprek.honza.rocketlaunch.feature.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veprek.honza.rocketlaunch.repository.RocketRepositoryImpl
import com.veprek.honza.rocketlaunch.repository.entity.ResponseWrapper
import com.veprek.honza.rocketlaunch.repository.model.Rocket
import com.veprek.honza.rocketlaunch.repository.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import quanti.com.kotlinlog.Log
import javax.inject.Inject

@HiltViewModel
class RocketDetailViewModel
@Inject constructor(private val rocketRepository: RocketRepositoryImpl) : ViewModel() {
    private val _rocket = MutableStateFlow(ResponseWrapper<Rocket?>(State.LOADING, null))
    val rocket: StateFlow<ResponseWrapper<Rocket?>> get() = _rocket

    fun getRocket(id: String) {
        viewModelScope.launch {
            rocketRepository.getRocket(id).collect { response ->
                Log.d("Response ${response.state}")
                _rocket.value = response
//                delay(1000)
            }
        }
    }
}