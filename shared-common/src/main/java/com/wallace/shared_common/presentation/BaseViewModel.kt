package com.wallace.shared_common.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wallace.shared_domain.common.ServiceErrorModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.koin.core.component.KoinComponent
import retrofit2.Response
import java.net.HttpURLConnection.*

open class BaseViewModel : ViewModel(), KoinComponent {

    private var _errorResponse = MutableLiveData<ServiceErrorModel>()
    var errorResponse: LiveData<ServiceErrorModel> = _errorResponse

    private var _shouldShowLoading = MutableLiveData<Boolean>()
    var shouldShowLoading: LiveData<Boolean> = _shouldShowLoading

    protected val apiException = CoroutineExceptionHandler { _, throwable ->
        showDefaultErrorMessage("Algo inesperado ocorreu")
    }

    protected fun showLoading() {
        _shouldShowLoading.value = true
    }

    protected fun dismissLoading() {
        _shouldShowLoading.value = false
    }

    protected suspend fun <T> serviceCaller(
        api: Response<T>?,
        onResponseError: ((ServiceErrorModel) -> Unit)? = null
    ): T? {
        showLoading()
        return resultHandling(api, onResponseError)
    }

    protected suspend fun <T> serviceSilentCaller(
        api: Response<T>?,
        onResponseError: ((ServiceErrorModel) -> Unit)? = null
    ) = resultHandling(api, onResponseError)

    private fun <T> resultHandling(
        api: Response<T>?,
        onResponseError: ((ServiceErrorModel) -> Unit)? = null
    ): T? {
        when {
            api?.code() == HTTP_OK -> {
                try {
                    dismissLoading()
                    if (api.body() == null)
                        errorResponse(api)

                    return api.body()
                } catch (exception: Exception) {
                    errorResponse(api)
                }
            }
            onResponseError != null -> {
                dismissLoading()
                val errorSerialized = api?.errorBody()?.charStream()?.readLines()?.get(0).toString()
                onResponseError.invoke(
                    ServiceErrorModel(
                        api?.code() ?: HTTP_INTERNAL_ERROR,
                        Throwable(errorSerialized)
                    )
                )
            }
            else -> {
                errorResponse(api)
            }
        }
        return null
    }

    private fun <T> errorResponse(api: Response<T>?) {
        dismissLoading()

        when (api?.code()) {
            HTTP_UNAUTHORIZED ->
                _errorResponse.value =
                    ServiceErrorModel(
                        HTTP_UNAUTHORIZED,
                        Throwable("Nao autorizado")
                    )
            in HTTP_INTERNAL_ERROR..599 ->
                _errorResponse.value =
                    ServiceErrorModel(
                        HTTP_INTERNAL_ERROR,
                        Throwable("Server error")
                    )
            else -> {
                val errorSerialized = api?.errorBody()?.charStream()?.readLines()?.get(0).toString()
                _errorResponse.value = ServiceErrorModel(
                    api?.code() ?: HTTP_INTERNAL_ERROR,
                    Throwable(errorSerialized)
                )
            }
        }
    }

    private fun showDefaultErrorMessage(message: String?) {
        _errorResponse.value =
            ServiceErrorModel(
                HTTP_INTERNAL_ERROR, Throwable(message ?: "")
            )
    }
}