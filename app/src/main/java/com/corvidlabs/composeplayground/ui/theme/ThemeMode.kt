package com.corvidlabs.composeplayground.ui.theme

/// User-selectable theme preference driving the app color scheme.
internal enum class ThemeMode {
    /// Follow the OS light/dark setting.
    System,

    /// Always use the light color scheme.
    Light,

    /// Always use the dark color scheme.
    Dark
}
