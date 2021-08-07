package com.example.daycoval_service

import com.example.daycoval_service.domain.repository.BaseRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.reflect.KClass

class DaycovalServiceDI {
    fun getModule(): Module {
        return module {
            single { BaseRepository<KClass<*>>(get()) }
        }
    }
}