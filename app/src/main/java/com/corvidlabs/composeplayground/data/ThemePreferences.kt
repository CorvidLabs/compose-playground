package com.corvidlabs.composeplayground.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.corvidlabs.composeplayground.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/// Single Preferences DataStore instance backing the persisted theme settings.
private val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/// Reads and writes the user's theme preferences so they survive process death.
internal class ThemePreferences(private val context: Context) {

    // MARK: - Keys

    private object Keys {
        val themeMode = stringPreferencesKey("theme_mode")
        val dynamicColor = booleanPreferencesKey("dynamic_color")
    }

    // MARK: - Reads

    /// Persisted theme mode, defaulting to [ThemeMode.System]; unknown values fall back safely.
    val themeMode: Flow<ThemeMode> = context.themeDataStore.data.map { preferences ->
        val raw = preferences[Keys.themeMode]
        ThemeMode.entries.firstOrNull { it.name == raw } ?: ThemeMode.System
    }

    /// Persisted dynamic color toggle, defaulting to `true`.
    val dynamicColor: Flow<Boolean> = context.themeDataStore.data.map { preferences ->
        preferences[Keys.dynamicColor] ?: true
    }

    // MARK: - Writes

    /// Persists the selected theme mode by its enum name.
    suspend fun setThemeMode(mode: ThemeMode) {
        context.themeDataStore.edit { preferences ->
            preferences[Keys.themeMode] = mode.name
        }
    }

    /// Persists the dynamic color toggle.
    suspend fun setDynamicColor(enabled: Boolean) {
        context.themeDataStore.edit { preferences ->
            preferences[Keys.dynamicColor] = enabled
        }
    }
}
