package com.aplikasi.dicodingevents.ui.settings

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit

class SettingsPreferences private constructor(private val dataStore: DataStore<Preferences>) {
    fun getThemeSettings(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }


    companion object {
        private val THEME_KEY = booleanPreferencesKey("theme_setting")

        @Volatile
        private var INSTANCE: SettingsPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingsPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingsPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}