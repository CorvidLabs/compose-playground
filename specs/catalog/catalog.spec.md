---
module: catalog
version: 2
status: active
files:
  - app/src/main/java/com/corvidlabs/composeplayground/model/Component.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/Registry.kt
  - app/src/main/java/com/corvidlabs/composeplayground/PlaygroundApp.kt
  - app/src/main/java/com/corvidlabs/composeplayground/MainActivity.kt

db_tables: []
depends_on: []
---

# Catalog

## Purpose

The catalog is the backbone of the Compose Playground app. It defines the component
model, the registry that aggregates every documented component from the per-group
files, and the navigation shell — a grouped, searchable home catalog plus a detail
page per component. Each component owns a longer description, related links, and a
list of interactive examples that each pair a live demo with its source code.

Adding a component is a two-step change: append a `Component(...)` to the relevant
`catalog/groups/<Group>.kt` file; no central edits are required because `Registry.kt`
aggregates the per-group lists.

## Public API

The app module is intentionally `internal` (Kotlin app-module convention); nothing is
exported across module boundaries. The catalog surface is:

### Structs & Enums

| Type | Description |
|------|-------------|
| `Component` | A documented component: `id`, `name`, `group`, `summary`, `description`, `docUrl?`, `related`, `examples`. |
| `CodeExample` | One example: `title`, `description`, `code` string, and a `@Composable` `demo`. |
| `ComponentGroup` | Enum of related-component sections (Actions, Communication, …, Graphics). |

### Functions

| Function | Signature | Description |
|----------|-----------|-------------|
| `allComponents` | `List<Component>` | Flat registry assembled from every group file. |
| `componentsByGroup` | `List<Pair<ComponentGroup, List<Component>>>` | Components bucketed by group, empty groups dropped. |
| `componentFor` | `(id: String?) -> Component?` | Resolves a navigation route id to its component. |
| `PlaygroundApp` | `@Composable () -> Unit` | Root `NavHost`: a `home` destination and a `component/{id}` detail route. |

## Invariants

1. Every `Component.id` is unique across `allComponents` (ids are NavHost route keys).
2. The home screen renders every component, grouped by `ComponentGroup` in enum order.
3. A blank search shows the grouped catalog; a non-blank query shows a flat, filtered
   list matching `name`, `summary`, or group label (case-insensitive).
4. Each example renders its live `demo` above a collapsible panel showing its `code`.
5. `related` ids that do not resolve are silently ignored (no crash, no dead row).

## Behavioral Examples

```
Given the user types a query on the home screen
When no component matches
Then a "No components match" empty state is shown

Given the user opens a component detail page
When the page renders
Then the description, each interactive example, and a Related section are shown
```

## Error Cases

| Error | When | Behavior |
|-------|------|----------|
| Unknown route id | `component/{id}` navigated with an unknown id | A "not found" screen with a back button is shown. |
| Unresolved related id | A `related` id has no matching component | That related row is omitted. |

## Dependencies

- Jetpack Compose (Material 3)
- Navigation Compose

## Change Log

| Version | Date | Changes |
|---------|------|---------|
| 1 | 2026-06-08 | Initial spec for the catalog + navigation shell |
| 2 | 2026-06-08 | Rework for the grouped/searchable gallery, component model, and detail pages |
