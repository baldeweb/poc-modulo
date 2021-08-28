package com.example.poc_modulo

import android.app.Application
import com.example.daycoval_service.DaycovalServiceDI
import com.example.details.DetailPokemonDI
import com.example.pokemon.PokemonDI
import com.example.shared_common.SharedCommonDI
import com.example.storage.DaoDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApplication: Application() {

    companion object {
        var instance: PokemonApplication? = null
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        val moduleList = listOf(
            DaoDI().getModule(),
            DaycovalServiceDI().getModule(),
            DetailPokemonDI().getModule(),
            PokemonDI().getModule(),
            SharedCommonDI().getModule()
        )

        startKoin {
            androidContext(this@PokemonApplication)
            modules(moduleList)
        }
    }
}