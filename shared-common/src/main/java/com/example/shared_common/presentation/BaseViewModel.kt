package com.example.shared_common.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shared_common.data.model.ServiceErrorModel
import com.example.shared_common.presentation.extension.SingleLiveEvent
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import retrofit2.Response
import java.net.HttpURLConnection.*

open class BaseViewModel : ViewModel(), KoinComponent {

    private var _errorResponse = MutableLiveData<ServiceErrorModel>()
    var errorResponse: LiveData<ServiceErrorModel> = _errorResponse

    private var _shouldShowLoading = MutableLiveData<Boolean>()
    var shouldShowLoading: LiveData<Boolean> = _shouldShowLoading

    protected suspend fun <T> serviceCaller(
        api: Response<T>?,
        onExecute: suspend (T) -> Unit,
        isSilentCall: Boolean? = null,
        onResponseError: ((ServiceErrorModel) -> Unit)? = null
    ) {
        if (isSilentCall == null || isSilentCall == false) showLoading()

        when {
            api?.code() == HTTP_OK -> {
                try {
                    api.body()?.let { onExecute.invoke(it) }
                    if (isSilentCall == null || isSilentCall == false) dismissLoading()
                } catch (exception: Exception) {
                    errorResponse(api)
                }
            }
            onResponseError != null -> {
                if (isSilentCall == null || isSilentCall == false) dismissLoading()
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

    protected fun showLoading() {
        _shouldShowLoading.value = true
    }

    protected fun dismissLoading() {
        _shouldShowLoading.value = false
    }

    private fun <T> errorResponse(api: Response<T>?) {
        dismissLoading()

        when (api?.code()) {
            HTTP_UNAUTHORIZED ->
                _errorResponse.value =
                    ServiceErrorModel(HTTP_UNAUTHORIZED, Throwable("Nao autorizado"))
            in HTTP_INTERNAL_ERROR..599 ->
                _errorResponse.value =
                    ServiceErrorModel(HTTP_INTERNAL_ERROR, Throwable("Server error"))
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