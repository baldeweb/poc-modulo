package com.example.pokemon.presentation

import android.os.Bundle
import android.util.Log
import com.example.daycoval_service.BaseActivity
import com.example.daycoval_service.observeNonNull
import com.example.pokemon.databinding.ActivityListPokemonBinding

class ListPokemonActivity : BaseActivity<PokemonViewModel>() {
    private lateinit var binding: ActivityListPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPokemonBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        viewModel.getPokemon()
        viewModel.pokemon.observeNonNull(this) {
            Log.d("LOG", "DADOS: $it")
        }
    }
}