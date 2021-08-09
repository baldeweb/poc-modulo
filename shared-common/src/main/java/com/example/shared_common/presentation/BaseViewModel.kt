package com.example.shared_common.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.core.component.KoinComponent
import java.net.SocketTimeoutException

open class BaseViewModel : ViewModel(), KoinComponent {
    protected val webServiceException = CoroutineExceptionHandler { _, exception ->

        Log.d("LOG", "exception(): ${exception.localizedMessage}")
        when (exception) {
            is SocketTimeoutException -> {

            }
        }
    }
}