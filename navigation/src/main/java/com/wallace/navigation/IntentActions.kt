package com.wallace.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.wallace.shared_common.presentation.constants.NavigationActionsConstants.DETAIL_POKEMON_ACTION_NAME

object IntentActions {
    fun openDetailPokemon(activity: AppCompatActivity) {
        activity.startActivity(
            Intent(DETAIL_POKEMON_ACTION_NAME).apply {
                setPackage(activity.packageName)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )
    }
}