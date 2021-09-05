package com.wallace.poc_modulo

import android.annotation.SuppressLint
import android.app.Application
import com.google.android.play.core.install.model.InstallStatus.*
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus.CANCELING
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION
import com.wallace.details.DetailPokemonDI
import com.wallace.pokemon.PokemonDI
import com.wallace.shared_common.SharedCommonDI
import com.wallace.shared_common.presentation.extension.StringUtils.logV
import com.wallace.shared_service.DaycovalServiceDI
import com.wallace.storage.DaoDI
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
        val request = SplitInstallRequest
            .newBuilder()
            .addModule("detail-private")
            .addModule("detail-public")
            .build()

        val splitInstallManager = SplitInstallManagerFactory.create(this)
        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener { logV("splitInstallManager >> addOnSuccessListener") }
            .addOnFailureListener { logV("splitInstallManager >> addOnFailureListener") }
            .addOnCompleteListener { logV("splitInstallManager >> addOnCompleteListener") }

        val stateListener = SplitInstallStateUpdatedListener { state ->
            when (state.status()) {
                PENDING -> { logV("stateListener >> PENDING") }
                DOWNLOADING -> { logV("stateListener >> DOWNLOADING") }
                DOWNLOADED -> { logV("stateListener >> DOWNLOADED") }
                INSTALLED -> { logV("stateListener >> INSTALLED") }
                INSTALLING -> { logV("stateListener >> INSTALLING") }
                REQUIRES_USER_CONFIRMATION -> { logV("stateListener >> REQUIRES_USER_CONFIRMATION") }
                FAILED -> { logV("stateListener >> FAILED") }
                CANCELING -> { logV("stateListener >> CANCELING") }
                CANCELED -> { logV("stateListener >> CANCELED") }
            }
        }
        splitInstallManager.registerListener(stateListener)
    }
}