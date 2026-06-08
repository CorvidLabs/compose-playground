# Accessibility

This document records the accessibility audit applied to the Compose Playground demo
surface (the catalog group files, the shared UI components, and the navigation host) and
the conventions the codebase follows going forward. The goal of the pass was a clean
TalkBack experience without altering any rendered pixels — every change is purely
semantic, so the Paparazzi snapshot goldens are unaffected.

## Scope audited

- `app/src/main/java/com/corvidlabs/composeplayground/PlaygroundApp.kt`
- `app/src/main/java/com/corvidlabs/composeplayground/ui/components/*.kt`
  (`ExampleCard`, `CodeBlock`)
- `app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/*.kt`
  (all 11 component groups)

## `contentDescription` policy

Every `Icon` carries an explicit `contentDescription`. The value is chosen by role:

- **Interactive icons that convey meaning** (icon buttons, FABs, chip remove/close
  affordances, the search field's clear button) get a concise, action- or
  meaning-oriented description — e.g. `"Back"`, `"Search"`, `"Settings"`, `"More"`,
  `"Clear search"`, `"Remove"`.
- **State-bearing toggle icons** describe their current state so a screen reader announces
  the change. For example the favorite `IconToggleButton` uses
  `if (checked) "Liked" else "Not liked"`, and the password field's visibility toggle uses
  `if (visible) "Hide password" else "Show password"`.
- **Decorative icons** — those sitting next to a text label that already conveys the
  meaning, leading/trailing chrome, list-item leading glyphs, navigation chevrons, and the
  "Show code"/"Hide code" expander glyphs — are explicitly set to
  `contentDescription = null` so TalkBack does not announce redundant noise.

Setting `null` deliberately (rather than supplying a label) is the correct, intentional
choice for decoration in Compose: it removes the node from the accessibility tree instead
of leaving an unlabeled element.

## Heading semantics

Section headings are marked with `Modifier.semantics { heading() }`. This adds no visual
change but lets TalkBack users jump heading-to-heading with the headings navigation
control:

- **Home group headers** (`GroupHeader`) — the group title for each catalog section.
- **Component detail example titles** (`ExampleCard`) — the title of each example card.
- **The "Related" section header** on the component detail screen.

The detail screen's component name lives in the Material 3 `TopAppBar` title, which is
already exposed as a header by the Material component, so it is left as-is. The intro
paragraph is descriptive prose rather than a heading and is intentionally not marked.

## Touch targets

All interactive controls use stock Material 3 components (`IconButton`,
`FloatingActionButton`, `Chip`, `TextButton`, `Switch`, etc.). These already enforce the
minimum 48dp x 48dp touch target via Material's built-in
`minimumInteractiveComponentEnforcement`, so no manual sizing was added — doing so would
also have moved pixels and broken the snapshot goldens.

## Verification

- `./gradlew verifyPaparazziDebug` — confirms no rendered pixels changed.
- `./gradlew assembleDebug` — confirms the app still compiles.
- Manual TalkBack sweep of the home catalog and a sample of component detail pages.
