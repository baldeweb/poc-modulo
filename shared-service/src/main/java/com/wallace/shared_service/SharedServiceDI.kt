package com.wallace.shared_service

import com.wallace.shared_service.repository.BaseRepository
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.reflect.KClass

class SharedServiceDI {
    fun getModule(): Module {
        return module {
            single { BaseRepository<KClass<*>>(get(), get()) }
        }
    }
}