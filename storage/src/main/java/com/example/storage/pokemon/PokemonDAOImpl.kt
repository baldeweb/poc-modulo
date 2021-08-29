package com.example.storage.pokemon

import android.content.SharedPreferences
import com.example.storage.dao.BaseDAO
import com.example.storage.dao.ConstantsDAO.ENDPOINT_URL_PREFS

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