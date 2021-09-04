package com.wallace.shared_common.mock

import android.content.Context
import android.content.res.AssetManager
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Invocation
import java.io.InputStream

class MockInterceptor(
    val context: Context,
    private val mockAllEnable: Boolean
) : Interceptor {

    private fun readFile(fileName: String): String {
        val manager: AssetManager = context.assets
        val file: InputStream = manager.open(fileName)
        val formArray = ByteArray(file.available())
        file.read(formArray)
        file.close()
        return String(formArray)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation = chain.request().tag(Invocation::class.java)
        val builder = chain.request().newBuilder()
        invocation?.method()?.annotations?.let { annotations ->
            annotations.forEach { annotation ->
                when (annotation.annotationClass) {
                    LoadMock::class -> {
                        (annotation as LoadMock).apply {
                            if (mockAllEnable) {
                                val response = readFile("mocks/$jsonFileName")
                                return chain.proceed(chain.request())
                                    .newBuilder()
                                    .code(200)
                                    .protocol(Protocol.HTTP_2)
                                    .message(response)
                                    .body(
                                        response
                                            .toByteArray()
                                            .toResponseBody(
                                                "application/json"
                                                    .toMediaTypeOrNull()
                                            )
                                    )
                                    .addHeader("content-type", "application/json")
                                    .build()
                            }
                        }
                    }
                }
            }
        }

        return chain.proceed(builder.build())
    }
}
