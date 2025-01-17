package com.veprek.honza.rocketlaunch.feature.detail.presentation

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veprek.honza.rocketlaunch.R
import com.veprek.honza.rocketlaunch.repository.RocketRepository
import com.veprek.honza.rocketlaunch.repository.api.DownloadManager
import com.veprek.honza.rocketlaunch.repository.entity.NoConnectionException
import com.veprek.honza.rocketlaunch.repository.entity.ResponseWrapper
import com.veprek.honza.rocketlaunch.repository.model.Rocket
import com.veprek.honza.rocketlaunch.repository.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import quanti.com.kotlinlog.Log

class RocketDetailViewModel(
    private val rocketRepository: RocketRepository,
    private val downloadManager: DownloadManager
) : ViewModel() {
    private val _rocket = MutableStateFlow(ResponseWrapper<Rocket?>(State.LOADING, null))
    val rocket: StateFlow<ResponseWrapper<Rocket?>> get() = _rocket

    fun getFile(id: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            var fileName: String? = null
            try {
                fileName = downloadManager.downloadFile(id)
            } catch (e: Exception) {
                if (e is NoConnectionException) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.file_offline),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Log.d("Other exception ${e.stackTraceToString()}")
                }
            }

            fileName?.let {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.file_saved, fileName),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun getRocket(id: String) {
        viewModelScope.launch {
            rocketRepository.getRocket(id).collect { response ->
                _rocket.value = response
            }
        }
    }
}
