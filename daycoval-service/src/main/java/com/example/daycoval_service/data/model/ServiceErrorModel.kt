package com.example.daycoval_service.data.model

open class ServiceErrorModel(
    var httpCode: Int = 0,
    var throwable: Throwable? = null
)