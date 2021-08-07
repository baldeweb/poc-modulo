package com.example.photogallery

import com.example.photogallery.domain.usecase.PhotoGalleryUseCase
import com.example.photogallery.domain.usecase.PhotoGalleryUseCaseImpl
import com.example.photogallery.presentation.PhotoGalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.get
import org.koin.dsl.module

class PhotoGalleryDI {
    fun getModule() : Module {
        return module {
            viewModel { PhotoGalleryViewModel() }
            single<PhotoGalleryUseCase> { PhotoGalleryUseCaseImpl() }
        }
    }
}