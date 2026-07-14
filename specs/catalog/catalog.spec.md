---
module: catalog
version: 4
status: active
files:
  - app/src/main/java/com/corvidlabs/composeplayground/MainActivity.kt
  - app/src/main/java/com/corvidlabs/composeplayground/PlaygroundApp.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/Registry.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/ActionsGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/AnimationGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/CommunicationGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/ContainmentGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/GesturesGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/GraphicsGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/LayoutGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/ListsGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/NavigationGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/SelectionGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/TextGroup.kt
  - app/src/main/java/com/corvidlabs/composeplayground/data/ThemePreferences.kt
  - app/src/main/java/com/corvidlabs/composeplayground/model/Component.kt
  - app/src/main/java/com/corvidlabs/composeplayground/previews/ComponentPreviews.kt
  - app/src/main/java/com/corvidlabs/composeplayground/ui/components/CodeBlock.kt
  - app/src/main/java/com/corvidlabs/composeplayground/ui/components/ExampleCard.kt
  - app/src/main/java/com/corvidlabs/composeplayground/ui/theme/Color.kt
  - app/src/main/java/com/corvidlabs/composeplayground/ui/theme/Theme.kt
  - app/src/main/java/com/corvidlabs/composeplayground/ui/theme/ThemeMode.kt
  - app/src/main/java/com/corvidlabs/composeplayground/ui/theme/Type.kt

db_tables: []
depends_on: []
---

# Catalog

## Purpose

The catalog is the complete Compose Playground application surface. It defines the
component model and per-group examples, the registry that aggregates every documented
component, the navigation shell, reusable example/code presentation, persisted theme
preferences, Material 3 theming, previews, and the Android composition root. The home
screen is grouped and searchable; each detail page pairs live demos with their source.

Adding a component is a two-step change: append a `Component(...)` to the relevant
`catalog/groups/<Group>.kt` file; no central edits are required because `Registry.kt`
aggregates the per-group lists.

## Public API

The app module is intentionally `internal` (Kotlin app-module convention); nothing is
exported across module boundaries. The catalog surface is:

### Exported Functions

SpecSync 5.0.1 reports Kotlin model properties and remembered demo state as detected
exports. They are internal to the application module; documenting them here records
their governed role without representing them as a public library API.

| Export | Description |
|--------|-------------|
| `checked` | Boolean selection state in the action examples. |
| `toggled` | Toggle state used by animation examples. |
| `color` | Animated color target used by an animation example. |
| `size` | Animated size target used by an animation example. |
| `visible` | Visibility state used by an animation example. |
| `count` | Counter state used by an animation example. |
| `page` | Page state used by an animation example. |
| `transition` | Transition state used by an animation example. |
| `alpha` | Animated opacity value used by an animation example. |
| `target` | Progress target used by a communication example. |
| `progress` | Progress value derived for a communication example. |
| `hostState` | Snackbar host state owned by a communication example. |
| `scope` | Coroutine scope owned by a communication example. |
| `open` | Visibility state for transient communication UI. |
| `state` | Component state used by communication examples. |
| `sheetState` | Bottom-sheet state used by a containment example. |
| `label` | User-visible gesture status text. |
| `offset` | Drag offset used by a gesture example. |
| `scale` | Transform scale used by a gesture example. |
| `rotation` | Transform rotation used by a gesture example. |
| `offsetX` | Horizontal transform offset used by a gesture example. |
| `offsetY` | Vertical transform offset used by a gesture example. |
| `scroll` | Scroll state used by a gesture example. |
| `items` | Current refreshable example items. |
| `isRefreshing` | Pull-to-refresh activity state. |
| `generation` | Keyed gesture-example generation counter. |
| `colors` | Graphics example color collection. |
| `accent` | Graphics example accent color. |
| `selected` | Selected navigation item. |
| `titles` | Navigation example tab/page titles. |
| `expanded` | Navigation menu expansion state. |
| `options` | Navigation example option labels. |
| `children` | Child checkbox states in a selection example. |
| `parentState` | Derived tri-state parent selection. |
| `wifi` | Wi-Fi switch state in a selection example. |
| `value` | Scalar selection control value. |
| `range` | Range selection control value. |
| `filters` | Filter-chip selection map. |
| `selectedIndex` | Selected segmented-button index. |
| `text` | General text-field value. |
| `email` | Email-field value. |
| `isError` | Derived validation error state. |
| `password` | Password-field value. |
| `query` | Search-field value. |
| `notes` | Multi-line notes-field value. |
| `title` | Example title |
| `description` | Component or example description |
| `code` | Example source text |
| `demo` | Interactive Compose example |
| `id` | Stable component route identifier |
| `name` | Display name |
| `group` | Component group |
| `summary` | Searchable short summary |
| `docUrl` | Optional external documentation URL |
| `related` | Related component identifiers |
| `examples` | Interactive examples |

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
| `PlaygroundApp` | `@Composable (ThemeMode, (ThemeMode) -> Unit, Boolean, (Boolean) -> Unit) -> Unit` | Root `NavHost`: a `home` destination and a `component/{id}` detail route, with live theme controls. |
| `ComposePlaygroundTheme` | `@Composable (Boolean, Boolean, @Composable () -> Unit) -> Unit` | Applies dynamic or static Material 3 light/dark colors and application typography. |
| `ThemePreferences` | `class (Context)` | Exposes persisted theme mode and dynamic-color flows and suspend update functions. |
| `ExampleCard` | `@Composable (CodeExample, Modifier) -> Unit` | Renders an example title, description, live demo, and collapsible source panel. |
| `CodeBlock` | `@Composable (String, Modifier, Boolean) -> Unit` | Renders horizontally scrollable monospaced source with explicit expand/collapse state. |

## Invariants

1. Every `Component.id` is unique across `allComponents` (ids are NavHost route keys).
2. The home screen renders every component, grouped by `ComponentGroup` in enum order.
3. A blank search shows the grouped catalog; a non-blank query shows a flat, filtered
   list matching `name`, `summary`, or group label (case-insensitive).
4. Each example renders its live `demo` above a collapsible panel showing its `code`.
5. `related` ids that do not resolve are silently ignored (no crash, no dead row).
6. Theme mode and dynamic-color preference changes persist through the single application DataStore.
7. Dynamic color is used only on Android 12+ when enabled; otherwise the selected static light/dark palette is used.
8. Every catalog entry belongs to exactly one declared group and carries at least one interactive example with displayed source.

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
| 3 | 2026-07-14 | Cover the complete existing app surface and stable governance requirements |
| 4 | 2026-07-14 | CHG-0001-adopt-specsync-5-0-1-and-the-unified-trust-1-0-0-governance-gate: Adopt SpecSync 5.0.1 and the unified Trust 1.0.0 governance gate |
