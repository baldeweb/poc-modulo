package com.wallace.details.presentation

import android.os.Bundle
import android.util.Log
import com.wallace.shared_common.presentation.BaseActivity
import com.wallace.details.databinding.ActivityDetailPokemonBinding
import com.wallace.shared_common.presentation.constants.KeyNameConstants.ENDPOINT
import com.wallace.shared_common.presentation.extension.observeNonNull

class DetailPokemonActivity : BaseActivity<DetailPokemonViewModel>() {
    private lateinit var binding: ActivityDetailPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        viewModel.getPokemonDetail(intent?.getStringExtra(ENDPOINT) ?: "")
        viewModel.pokemonDetail.observeNonNull(this) {
            Log.d("LOG", "getPokemonDetail(): $it")
        }
    }
}