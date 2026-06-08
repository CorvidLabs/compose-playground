---
module: catalog
version: 1
status: active
files:
  - app/src/main/java/com/corvidlabs/composeplayground/Catalog.kt
  - app/src/main/java/com/corvidlabs/composeplayground/PlaygroundApp.kt
  - app/src/main/java/com/corvidlabs/composeplayground/MainActivity.kt

db_tables: []
depends_on: []
---

# Catalog

## Purpose

The catalog module is the backbone of the Compose Playground app. It defines the
registry of component demos and the navigation shell that lets a user browse the
home list and drill into a per-component showcase screen. Adding a new demo is a
two-step change: author a `@Composable (PaddingValues) -> Unit` under `demos/` and
register one `Demo` entry here.

## Public API

The app module is intentionally `internal` (Kotlin app-module convention), so no
symbols are exported across module boundaries. The catalog surface is:

### Structs & Enums

| Type | Description |
|------|-------------|
| `Demo` | Immutable descriptor for one showcase: `route`, `title`, `subtitle`, `icon`, and a `content` composable. |

### Functions

| Function | Signature | Description |
|----------|-----------|-------------|
| `demos` | `List<Demo>` | Ordered registry of every showcase rendered on the home screen. |
| `demoFor` | `(route: String?) -> Demo?` | Resolves a navigation route back to its `Demo`. |
| `PlaygroundApp` | `@Composable () -> Unit` | Root `NavHost`: a `home` catalog destination plus one destination per demo route. |

## Invariants

1. Every `Demo.route` is unique across `demos` (routes are NavHost destination keys).
2. Every `Demo.route` has a matching `composable(route)` destination registered by `PlaygroundApp`.
3. The home list renders exactly one row per entry in `demos`, in list order.
4. Each demo's `content` receives the scaffold inner padding and lays out edge-to-edge beneath the top app bar.

## Behavioral Examples

```
Given the app is launched
When the home screen renders
Then it shows one tappable row per entry in `demos`

Given the user taps a catalog row
When navigation occurs
Then the matching demo's detail screen is pushed with a back-enabled top app bar
```

## Error Cases

| Error | When | Behavior |
|-------|------|----------|
| Unknown route | `demoFor` is called with a route not in `demos` | Returns `null` (no crash). |
| Back from detail | User presses the top-bar back button or system back | `popBackStack()` returns to the home catalog. |

## Dependencies

- Jetpack Compose (Material 3)
- Navigation Compose

## Change Log

| Version | Date | Changes |
|---------|------|---------|
| 1 | 2026-06-08 | Initial spec for the catalog + navigation shell |
