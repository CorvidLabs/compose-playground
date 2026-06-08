# Compose Playground

A single-app **Jetpack Compose** catalog that showcases the Material 3 component library — buttons, text fields, selection controls, chips, cards, dialogs, sheets, lists, tabs, badges, app bars, animation, and more.

Each entry on the home screen opens a dedicated demo screen with live, interactive examples.

## Stack

| | |
|---|---|
| Language | Kotlin 2.0.21 |
| UI | Jetpack Compose + Material 3 |
| Navigation | Navigation Compose |
| Build | AGP 8.7.3 / Gradle 8.9 |
| SDK | compileSdk 35 · minSdk 24 |

## Catalog

- **Buttons** — filled, tonal, elevated, outlined, text, icon, toggle, FABs
- **Text & Typography** — type scale, annotated spans, overflow, selection
- **Text Fields** — filled, outlined, error state, password toggle, search, multi-line
- **Selection Controls** — checkbox, tri-state, switch, radio group
- **Sliders** — continuous, stepped, range
- **Chips** — assist, filter, input, suggestion
- **Cards & Surfaces** — filled, elevated, outlined, clickable
- **Progress** — circular & linear, indeterminate & determinate
- **Dialogs** — alert and fully custom
- **Bottom Sheets** — modal sheet with actions
- **Snackbars** — simple and with action/result
- **Lazy Lists & Grids** — ListItem column, LazyRow, LazyVerticalGrid
- **Tabs** — fixed and scrollable
- **Badges** — dot, count, live counter
- **App Bars** — top, center-aligned, navigation bar
- **Layout** — Row/Column/Box, weights, arrangement, alignment
- **Animation** — `animate*AsState`, `AnimatedVisibility`, `Crossfade`

## Run

```bash
./gradlew assembleDebug      # build the APK
./gradlew installDebug       # install on a connected device/emulator
```

Open the project in Android Studio and run the `app` configuration on a device or emulator (API 24+).

## Project layout

```
app/src/main/java/com/corvidlabs/composeplayground/
├── MainActivity.kt          # entry point, sets the theme
├── PlaygroundApp.kt         # NavHost: home catalog + detail screens
├── Catalog.kt               # registry of all demos
├── ui/                      # theme + shared layout helpers
└── demos/                   # one file per component category
```

Adding a demo is two steps: write a `@Composable (PaddingValues) -> Unit` in `demos/`, then add one `Demo(...)` entry to the list in `Catalog.kt`.
