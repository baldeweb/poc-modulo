package com.wallace.storage

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.wallace.storage.dao.ConstantsDAO.SHARED_PREFERENCES
import com.wallace.storage.dao.BaseDAO
import com.wallace.storage.pokemon.PokemonDAO
import com.wallace.storage.pokemon.PokemonDAOImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

class DaoDI {
    fun getModule(): Module {
        return module {
            factory { getSharedPrefs(androidApplication()) }
            factory<SharedPreferences.Editor> { getSharedPrefs(androidApplication()).edit() }

            single { BaseDAO(get()) }
            single<PokemonDAO> { PokemonDAOImpl(get()) }
        }
    }

    private fun getSharedPrefs(androidApplication: Application): SharedPreferences =
        androidApplication.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE)
}