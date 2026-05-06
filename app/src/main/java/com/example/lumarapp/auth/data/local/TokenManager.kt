package com.example.lumarapp.auth.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val USER_ID_KEY = stringPreferencesKey("id")
        private val NAME_KEY = stringPreferencesKey("name")
        private val ROL_KEY = intPreferencesKey("rol")
    }

    suspend fun saveSession(token: String, id: String, name: String, rol: Int) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
            prefs[USER_ID_KEY] = id
            prefs[NAME_KEY] = name
            prefs[ROL_KEY] = rol
        }
    }

    suspend fun getToken(): String? {
        return context.dataStore.data.map { it[TOKEN_KEY] }.first()
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { it[TOKEN_KEY] != null }

    suspend fun getName(): String? {
        return context.dataStore.data.map { it[NAME_KEY] }.first()
    }

    suspend fun getRol(): Int? {

        return context.dataStore.data.map { it [ROL_KEY] }.first()
    }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}
