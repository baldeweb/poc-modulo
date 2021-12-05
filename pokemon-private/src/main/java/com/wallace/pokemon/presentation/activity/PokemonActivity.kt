package com.wallace.pokemon.presentation.activity

import android.os.Bundle
import com.wallace.shared_common.presentation.BaseActivity
import com.wallace.shared_common.presentation.extension.observeNonNull
import com.wallace.pokemon.databinding.ActivityListPokemonBinding
import com.wallace.pokemon.presentation.viewmodel.PokemonViewModel
import com.wallace.shared_common.presentation.extension.StringUtils.logD

class PokemonActivity : BaseActivity<PokemonViewModel>() {
    private lateinit var binding: ActivityListPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPokemonBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        viewModel.pokemon.observeNonNull(this) {
            logD("======= RESPONSE ======= \n $it")
        }

        initListeners()
    }

    private fun initListeners() {
        binding.edtPokemonName.setOnEditorActionListener { v, actionId, event ->
            //viewModel.redirectDetailPokemon(this)
            viewModel.getPokemon(this, binding.edtPokemonName.text.toString())
            return@setOnEditorActionListener true
        }

        binding.btnPokemonDetalhes.setOnClickListener {
            //viewModel.redirectDetailPokemon(this)
            viewModel.getPokemon(this, binding.edtPokemonName.text.toString())
        }
    }
}