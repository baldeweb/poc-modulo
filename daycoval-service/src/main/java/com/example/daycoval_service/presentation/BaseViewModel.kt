package com.example.daycoval_service.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.core.component.KoinComponent
import java.net.SocketTimeoutException

open class BaseViewModel : ViewModel(), KoinComponent {
    protected val webServiceException = CoroutineExceptionHandler { _, exception ->

        when (exception) {
            is SocketTimeoutException -> {

            }
        }
    }
}