package com.example.pokemon.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.SocketTimeoutException

open class BaseViewModel : ViewModel() {
    protected val webServiceException = CoroutineExceptionHandler { _, exception ->

        when (exception) {
            is SocketTimeoutException -> {

            }
        }
    }
}