package com.example.details.presentation

import android.os.Bundle
import android.util.Log
import com.example.daycoval_service.presentation.BaseActivity
import com.example.details.databinding.ActivityDetailPokemonBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPokemonActivity : BaseActivity<DetailPokemonViewModel>() {
    private lateinit var binding: ActivityDetailPokemonBinding
    private val myViewModel: DetailPokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        val endpointBundle = intent?.getStringExtra("ENDPOINT") ?: ""
        Log.d("LOG", "endpointBundle: $endpointBundle")
        myViewModel.getPokemonDetail(endpointBundle)
    }
}