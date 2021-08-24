package com.example.shared_common.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shared_common.data.model.ServiceErrorModel
import org.koin.core.component.KoinComponent
import retrofit2.Response
import java.net.HttpURLConnection.*

open class BaseViewModel : ViewModel(), KoinComponent {

    private var _errorResponse = MutableLiveData<ServiceErrorModel>()
    var errorResponse: LiveData<ServiceErrorModel> = _errorResponse

    protected suspend fun <T> serviceCaller(
        api: Response<T>?,
        onExecute: suspend (T) -> Unit
    ) {
        serviceCaller(api, onExecute)
    }

    protected suspend fun <T> serviceCaller(
        api: Response<T>?,
        onExecute: suspend (T) -> Unit,
        onResponseError: ((ServiceErrorModel) -> Unit)? = null
    ) {
        when {
            api?.code() == HTTP_OK -> {
                try {
                    api.body()?.let { onExecute.invoke(it) }
                } catch (exception: Exception) {
                    errorResponse(api)
                }
            }
            onResponseError != null -> {
                val errorSerialized = api?.errorBody()?.charStream()?.readLines()?.get(0).toString()
                onResponseError.invoke(
                    ServiceErrorModel(
                        api?.code() ?: HTTP_INTERNAL_ERROR,
                        Throwable(errorSerialized)
                    )
                )
                return
            }
            else -> {
                errorResponse(api)
            }
        }
    }

    private fun <T> errorResponse(api: Response<T>?) {
        when (api?.code()) {
            HTTP_UNAUTHORIZED ->
                _errorResponse.value =
                    ServiceErrorModel(HTTP_UNAUTHORIZED, Throwable("Nao autorizado"))
            in HTTP_INTERNAL_ERROR..599 ->
                _errorResponse.value =
                    ServiceErrorModel(HTTP_UNAUTHORIZED, Throwable("Server error"))
            else -> {
                val errorSerialized = api?.errorBody()?.charStream()?.readLines()?.get(0).toString()
                _errorResponse.value = ServiceErrorModel(
                    api?.code() ?: HTTP_INTERNAL_ERROR,
                    Throwable(errorSerialized)
                )
            }
        }
    }
}