package com.wallace.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.wallace.navigation.ConstantsActions.DETAIL_POKEMON_ACTION
import com.wallace.shared_common.presentation.constants.KeyNameConstants.ENDPOINT

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