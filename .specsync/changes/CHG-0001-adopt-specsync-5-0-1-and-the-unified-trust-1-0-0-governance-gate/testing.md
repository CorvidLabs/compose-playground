---
change: CHG-0001-adopt-specsync-5-0-1-and-the-unified-trust-1-0-0-governance-gate
artifact: testing
---

# Testing

- `specsync check --strict --require-coverage 100 --force`
- `specsync agents status`
- `fledge trust doctor`
- `fledge lanes run verify`
- Exact-head hosted Android build, Trust, CodeQL, and independent Astro/Pages workflows

Requirement evidence:

- `REQ-catalog-001`, `REQ-catalog-002`, and `REQ-catalog-004`: `NavigationTest` and registry/navigation execution in `testDebugUnitTest`.
- `REQ-catalog-003` and `REQ-catalog-007`: complete group registry construction plus `GallerySnapshotTest.componentShowcaseLight`.
- `REQ-catalog-005` and `REQ-catalog-006`: application build, theme composition in navigation/snapshot tests, and the persisted preference source contract.
- `REQ-catalog-008`: `verifyPaparazziDebug`, including the fixed-date picker fixture and committed home/light/dark images.
