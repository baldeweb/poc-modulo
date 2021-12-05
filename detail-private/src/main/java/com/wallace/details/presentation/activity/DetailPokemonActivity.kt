package com.wallace.details.presentation.activity

import android.os.Bundle
import com.wallace.shared_common.presentation.BaseActivity
import com.wallace.details.databinding.ActivityDetailPokemonBinding
import com.wallace.details.presentation.viewmodel.DetailPokemonViewModel
import com.wallace.shared_common.presentation.extension.StringUtils.logD
import com.wallace.shared_common.presentation.extension.observeNonNull

class DetailPokemonActivity : BaseActivity<DetailPokemonViewModel>() {
    private lateinit var binding: ActivityDetailPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        viewModel.getPokemonDetail()
        viewModel.pokemonDetail.observeNonNull(this) {
            logD("getPokemonDetail(): $it")
        }
    }
}