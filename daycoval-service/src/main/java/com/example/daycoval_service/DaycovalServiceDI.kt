package com.example.daycoval_service

import com.example.daycoval_service.domain.repository.BaseRepository
import com.example.daycoval_service.presentation.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.reflect.KClass

class DaycovalServiceDI {
    fun getModule(): Module {
        return module {
            viewModel { BaseViewModel() }
            single { BaseRepository<KClass<*>>(get()) }
        }
    }
}