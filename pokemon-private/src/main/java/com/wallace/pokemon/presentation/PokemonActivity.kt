package com.wallace.pokemon.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import com.wallace.shared_common.presentation.BaseActivity
import com.wallace.shared_common.presentation.extension.observeNonNull
import com.wallace.pokemon.databinding.ActivityListPokemonBinding
import com.wallace.shared_common.presentation.extension.StringUtils.logD

class PokemonActivity : BaseActivity<PokemonViewModel>() {
    private lateinit var binding: ActivityListPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPokemonBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        viewModel.getPokemon()
        viewModel.pokemon.observeNonNull(this) {
            logD("DADOS: $it")
        }
    }

    fun buscarDetalhes(v: View) {
        viewModel.redirectDetailPokemon(this)
    }
}