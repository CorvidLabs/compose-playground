---
change: CHG-0003-replace-generic-sdd-paths-with-the-complete-repository-specific-delivery-and-gov
artifact: testing
---

# Testing

- Parse `.specsync/sdd.json` and inspect every meaningful and ignored path.
- `specsync check --strict --require-coverage 100 --force` remains at 23/23 files and 5,447/5,447 LOC.
- `specsync agents status` reports all four integrations installed.
- `actionlint` validates the hosted Build and Trust workflows.
- `fledge lanes run verify` repeats the APK build, six JVM/UI tests, and Paparazzi verification.
- `fledge trust verify --range origin/main..HEAD` passes with expected progressive provenance.
- Exact-head hosted Build, Trust, and CodeQL must pass before the pull request becomes ready.
