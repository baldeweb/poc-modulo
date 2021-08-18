package com.example.details.presentation

import android.os.Bundle
import android.util.Log
import com.example.shared_common.presentation.BaseActivity
import com.example.details.databinding.ActivityDetailPokemonBinding

class DetailPokemonActivity : BaseActivity<DetailPokemonViewModel>() {
    private lateinit var binding: ActivityDetailPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        val endpointBundle = intent?.getStringExtra("ENDPOINT") ?: ""
        Log.d("LOG", "endpointBundle: $endpointBundle")
        viewModel.getPokemonDetail(endpointBundle)
    }
}