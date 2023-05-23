package com.example.buyerapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class GlobalKeyValueCache @Inject constructor(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "global_cache")

    private object PreferencesKey {
        val authKey = stringPreferencesKey(name = "auth_key")
    }

    private val dataStore = context.dataStore;

    suspend fun putAuthKey(key: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.authKey] = key
        }
    }

    suspend fun cleaAuthKey() {
        dataStore.edit { preferences ->
            preferences.remove(PreferencesKey.authKey)
        }
    }


    suspend fun getAuthKey(): Flow<String?> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
            .map { preferences ->
                preferences[PreferencesKey.authKey]
            }
    }
}