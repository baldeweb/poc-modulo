package com.example.pokemon.business.repository

import android.content.Context

open class BaseRepository<T>(private val api: T, private val context: Context)