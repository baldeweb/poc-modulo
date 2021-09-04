package com.wallace.pokemon.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import com.wallace.shared_common.presentation.BaseActivity
import com.wallace.shared_common.presentation.extension.observeNonNull
import com.wallace.pokemon.databinding.ActivityListPokemonBinding

class PokemonActivity : BaseActivity<PokemonViewModel>() {
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

    fun buscarDetalhes(view: View) {
        viewModel.redirectDetailPokemon(this)
    }
}