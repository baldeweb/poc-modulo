package com.wallace.poc_modulo

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.wallace.details.DetailPokemonDI
import com.wallace.pokemon.PokemonDI
import com.wallace.shared_common.SharedCommonDI
import com.wallace.shared_service.SharedServiceDI
import com.wallace.storage.DaoDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApplication: SplitCompatApplication() {

    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(
            DaoDI().getModule(),
            DetailPokemonDI().getModule(),
            PokemonDI().getModule(),
            SharedCommonDI().getModule(),
            SharedServiceDI().getModule()
        )

        startKoin {
            androidContext(this@PokemonApplication)
            modules(moduleList)
        }
    }
}