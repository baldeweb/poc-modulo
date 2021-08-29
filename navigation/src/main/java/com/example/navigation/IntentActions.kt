package com.example.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.ActionName.DETAIL_POKEMON_ACTION
import com.example.navigation.KeyExtrasAction.ENDPOINT

object IntentActions {
    fun openDetailPokemon(activity: AppCompatActivity, endpoint: String) {
        activity.startActivity(
            Intent(DETAIL_POKEMON_ACTION).apply {
                putExtra(ENDPOINT, endpoint)
                setPackage(activity.packageName)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }
}