package com.wallace.details.presentation.activity

import android.os.Bundle
import com.wallace.details.databinding.ActivityPokemonImageBinding
import com.wallace.details.presentation.viewmodel.DetailPokemonViewModel
import com.wallace.shared_common.presentation.BaseActivity

class PokemonImageActivity : BaseActivity<DetailPokemonViewModel>() {
    lateinit var binding: ActivityPokemonImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}