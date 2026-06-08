package com.corvidlabs.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.corvidlabs.composeplayground.data.ThemePreferences
import com.corvidlabs.composeplayground.ui.theme.ComposePlaygroundTheme
import com.corvidlabs.composeplayground.ui.theme.ThemeMode
import kotlinx.coroutines.launch

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val themePreferences = ThemePreferences(applicationContext)

        setContent {
            val scope = rememberCoroutineScope()
            val themeMode by themePreferences.themeMode.collectAsState(initial = ThemeMode.System)
            val dynamicColor by themePreferences.dynamicColor.collectAsState(initial = true)

            val darkTheme = when (themeMode) {
                ThemeMode.System -> isSystemInDarkTheme()
                ThemeMode.Light -> false
                ThemeMode.Dark -> true
            }

            ComposePlaygroundTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlaygroundApp(
                        themeMode = themeMode,
                        onThemeModeChange = { mode ->
                            scope.launch { themePreferences.setThemeMode(mode) }
                        },
                        dynamicColor = dynamicColor,
                        onDynamicColorChange = { enabled ->
                            scope.launch { themePreferences.setDynamicColor(enabled) }
                        }
                    )
                }
            }
        }
    }
}
