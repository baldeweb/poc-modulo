package com.example.daycoval_service.model

open class ServiceErrorModel(
    var httpCode: Int = 0,
    var throwable: Throwable? = null
)