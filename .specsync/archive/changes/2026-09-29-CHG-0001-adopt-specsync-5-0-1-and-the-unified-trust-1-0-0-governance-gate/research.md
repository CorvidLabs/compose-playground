---
change: CHG-0001-adopt-specsync-5-0-1-and-the-unified-trust-1-0-0-governance-gate
artifact: research
---

# Research

The active catalog contract previously mapped only 4 of 23 application source files. The existing source is one cohesive catalog application: grouped example definitions, registry/navigation, source presentation, persisted theme controls, Material 3 theming, previews, and the Android composition root. Mapping that complete surface yields 100% file and line coverage without inventing modules.

Native verification found one pre-existing Paparazzi failure: the picker example used the wall clock, so its selected date and displayed month changed after the baseline was recorded. A test-only fixed UTC date makes the existing visual contract deterministic without changing product behavior. Android verification already runs through Fledge and can be reused without introducing a second build system.

SpecSync 5.0.1's Kotlin detector classifies remembered local demo values as exports. The contract records all 56 detected names as internal application state, clearly distinguishes them from public library API, and preserves strict export coverage without changing product source.
