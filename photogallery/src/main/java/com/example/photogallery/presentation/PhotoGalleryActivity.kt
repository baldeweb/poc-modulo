package com.example.photogallery.presentation

import android.os.Bundle
import com.example.daycoval_service.presentation.BaseActivity
import com.example.photogallery.databinding.ActivityPhotoGalleryBinding

class PhotoGalleryActivity : BaseActivity<PhotoGalleryViewModel>() {
    private lateinit var binding: ActivityPhotoGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoGalleryBinding.inflate(layoutInflater)
        super.setContentView(binding.root)


    }
}