package com.wallace.poc_modulo

import android.annotation.SuppressLint
import android.app.Application
import com.wallace.shared_service.DaycovalServiceDI
import com.wallace.details.DetailPokemonDI
import com.wallace.pokemon.PokemonDI
import com.wallace.shared_common.SharedCommonDI
import com.wallace.storage.DaoDI
import com.google.android.play.core.install.model.InstallStatus.*
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus.CANCELING
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION
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

        dynamicFeature()
    }

    @SuppressLint("SwitchIntDef")
    private fun dynamicFeature() {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val request = SplitInstallRequest
            .newBuilder()
            .addModule("detail-private")
            .addModule("detail-public")
            .build()
        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
            .addOnCompleteListener {  }

        val stateListener = SplitInstallStateUpdatedListener { state ->
            when (state.status()) {
                PENDING -> { }
                DOWNLOADING -> { }
                DOWNLOADED -> { }
                INSTALLED -> { }
                INSTALLING -> { }
                REQUIRES_USER_CONFIRMATION -> { }
                FAILED -> { }
                CANCELING -> { }
                CANCELED -> { }
            }
        }
        splitInstallManager.registerListener(stateListener)
    }
}