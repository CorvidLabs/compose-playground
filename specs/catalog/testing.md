---
spec: catalog.spec.md
---

## Test Plan

### Unit Tests

- `NavigationTest` verifies `REQ-catalog-001` through `REQ-catalog-004`: catalog rendering, detail navigation, and search filtering.
- Registry construction and the complete unit-test build exercise the persisted theme and catalog-entry contracts in `REQ-catalog-005` through `REQ-catalog-007`.

### Integration Tests

- `GallerySnapshotTest` verifies `REQ-catalog-003`, `REQ-catalog-006`, and `REQ-catalog-008` with deterministic light, dark, component, and home-catalog images.
- Build the debug APK, run all JVM tests, and verify every committed Paparazzi snapshot.
