---
spec: catalog.spec.md
---

## Test Plan

### Unit Tests

- `NavigationTest.homeShowsCatalog` asserts that the home screen renders the catalog title, an Actions
  group label, and the Button entry. This covers the visible-home subset of `REQ-catalog-002`.
- `NavigationTest.searchFiltersList` asserts case-insensitive matching for `slider` by showing Slider
  and excluding Button. This covers the filtering subset of `REQ-catalog-002`.
- `NavigationTest.openComponentDetail` opens Button and asserts its `Emphasis levels` example title.
  This covers detail navigation and part of the live-example surface in `REQ-catalog-003`.
- The current unit tests do not directly assert duplicate-id rejection (`REQ-catalog-001`),
  unknown-route and unresolved-related recovery (`REQ-catalog-004`), DataStore persistence
  (`REQ-catalog-005`), Android-version-dependent dynamic color (`REQ-catalog-006`), or every
  group/example/source invariant (`REQ-catalog-007`). A successful build proves those paths compile;
  it is not behavioral evidence for those requirements.

### Integration Tests

- `GallerySnapshotTest.componentShowcaseLight` renders every registered component or its curated
  deterministic representative. It is visual smoke coverage related to `REQ-catalog-003` and
  `REQ-catalog-007`, but it does not assert source-panel pairing or registry invariants.
- `GallerySnapshotTest.componentShowcaseDark` renders five named dark-theme representatives, and
  `homeCatalog` renders deterministic light and dark home images. These cover the static-theme and
  representative-image portions of `REQ-catalog-006` and `REQ-catalog-008`; they do not exercise
  Android 12 dynamic-color selection.
- The fixed picker date and month used by `componentShowcaseLight` make the committed picker baseline
  independent of wall-clock time, directly covering the deterministic-fixture portion of
  `REQ-catalog-008`.
- Build the debug APK, run all JVM tests, and verify every committed Paparazzi snapshot.
