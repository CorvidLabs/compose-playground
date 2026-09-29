---
change: CHG-0005-make-the-specialized-self-hosted-macos-ci-lane-manual-only-so-repositories-witho
artifact: testing
---

# Testing

- `actionlint .github/workflows/macos-selfhosted.yml` validates the manual-only workflow syntax.
- Event inspection confirms `workflow_dispatch` is the only trigger and all `uses` entries are SHA-pinned.
- `specsync check --strict --force --require-coverage 100` remains at full repository coverage.
- `fledge lanes run verify` runs the APK build, JVM/UI tests, and Paparazzi verification.
- `fledge trust verify --range origin/main..HEAD` validates lifecycle, contract, risk, and provenance policy.
- Exact-head hosted checks and a clean conflict/review-thread audit are required before readiness.
