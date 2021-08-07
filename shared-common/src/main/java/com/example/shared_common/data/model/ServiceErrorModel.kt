package com.example.shared_common.data.model

open class ServiceErrorModel(
    var httpCode: Int = 0,
    var throwable: Throwable? = null
)