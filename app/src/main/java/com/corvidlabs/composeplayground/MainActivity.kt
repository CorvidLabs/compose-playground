package com.corvidlabs.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.corvidlabs.composeplayground.ui.theme.ComposePlaygroundTheme
import com.corvidlabs.composeplayground.ui.theme.ThemeMode

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            // TODO: persist these preferences with DataStore so they survive process death.
            var themeMode by rememberSaveable { mutableStateOf(ThemeMode.System) }
            var dynamicColor by rememberSaveable { mutableStateOf(true) }

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
                        onThemeModeChange = { themeMode = it },
                        dynamicColor = dynamicColor,
                        onDynamicColorChange = { dynamicColor = it }
                    )
                }
            }
        }
    }
}
