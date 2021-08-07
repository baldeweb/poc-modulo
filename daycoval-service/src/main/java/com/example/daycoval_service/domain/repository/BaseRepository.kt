package com.example.daycoval_service.domain.repository

import com.example.daycoval_service.data.model.ServiceErrorModel
import retrofit2.Response
import java.io.IOException

open class BaseRepository<T>(private val api: T) {
    private val HTTP_OK = 200
    private val HTTP_UNAUTHORIZED = 401
    private val HTTP_INTERNAL_SERVER_ERROR = 500

    protected fun <T> serviceCaller(
        api: Response<T>,
        onSuccess: (T) -> Unit,
        onError: (ServiceErrorModel) -> Unit
    ) {
        if (api.code() == HTTP_OK) {
            try {
                if (api.body() == null) {
                    errorResponse(api, onError)
                    return
                }
                api.body()?.let { onSuccess.invoke(it) }
            } catch (exception: IOException) {
                errorResponse(api, onError)
            }
        } else {
            errorResponse(api, onError)
        }
    }

    private fun errorResponse(
        api: Response<*>,
        onError: (ServiceErrorModel) -> Unit
    ) {
        try {
            applyThrowableResponse(api, onError)
        } catch (exception: IOException) {
            onError.invoke(ServiceErrorModel(api.code(), Throwable(api.errorBody().toString())))
        }
    }

    @Synchronized
    private fun applyThrowableResponse(
        api: Response<*>,
        onError: (ServiceErrorModel) -> Unit
    ) {
        val error = ServiceErrorModel()
        error.httpCode = api.code()

        val errorSerialized = (api.errorBody()?.bytes() as ByteArray).toString()
        error.throwable = Throwable(errorSerialized)

        when (error.httpCode) {
            HTTP_UNAUTHORIZED -> onError.invoke(error)
            in HTTP_INTERNAL_SERVER_ERROR..599 -> onError.invoke(error)
            else -> onError.invoke(ServiceErrorModel(500))
        }
    }
}