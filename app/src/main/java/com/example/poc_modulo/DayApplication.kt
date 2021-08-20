package com.example.poc_modulo

import android.app.Application
import com.example.daycoval_service.DaycovalServiceDI
import com.example.details.DetailPokemonDI
import com.example.pokemon.PokemonDI
import com.example.shared_common.SharedCommonDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DayApplication: Application() {

    companion object {
        var instance: DayApplication? = null
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        val moduleList = listOf(
            DaycovalServiceDI().getModule(),
            DetailPokemonDI().getModule(),
            PokemonDI().getModule(),
            SharedCommonDI().getModule()
        )

        startKoin {
            androidContext(this@DayApplication)
            modules(moduleList)
        }
    }
}