package com.wallace.storage.pokemon

import android.content.SharedPreferences
import com.wallace.storage.dao.BaseDAO
import com.wallace.storage.dao.ConstantsDAO.ENDPOINT_URL_PREFS

class PokemonDAOImpl(
    private val sharedPreferences: SharedPreferences
) : BaseDAO(sharedPreferences), PokemonDAO {

    override suspend fun saveDetailPokemonUrl(url: String) {
        edit.putString(ENDPOINT_URL_PREFS, url)
        edit.commit()
    }

    override suspend fun getDetailPokemonUrl(keyPreference: String): String =
        sharedPreferences.getString(keyPreference, "") ?: ""
}