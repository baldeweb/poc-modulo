package com.example.details.presentation

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.daycoval_service.presentation.BaseNavigation
import com.example.daycoval_service.presentation.DetailPokemonNavigation

class DetailPokemonNavigationImpl(
    private val context: Context
) : BaseNavigation(), DetailPokemonNavigation {
    override fun redirectDetailPokemon(endpoint: String) {
        Log.d("LOG", "redirectDetailPokemon: $endpoint")
        Intent(context, DetailPokemonActivity::class.java).apply {
            putExtra("ENDPOINT", endpoint)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}