package com.example.daycoval_service

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, function: (T) -> Unit) {
    observe(lifecycleOwner, Observer {
        if (it != null) function.invoke(it)
        else return@Observer
    })
}