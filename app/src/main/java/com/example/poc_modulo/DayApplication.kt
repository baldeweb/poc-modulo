package com.example.poc_modulo

import android.app.Application
import com.example.daycoval_service.DaycovalServiceDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class DayApplication: Application() {

    companion object {
        var instance: DayApplication? = null
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        val moduleList = listOf(
            DaycovalServiceDI().getModule(),
        )

        startKoin {
            androidContext(this@DayApplication)
            modules(moduleList)
        }
    }
}