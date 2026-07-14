---
change: CHG-0004-record-explicit-lifecycle-ownership-for-the-committed-trust-augur-attest-and
artifact: testing
---

# Testing

- Strict lifecycle range validation reports no uncovered meaningful paths.
- `specsync check --strict --require-coverage 100 --force` remains at 23/23 files and 5,447/5,447 LOC.
- `fledge lanes run verify` repeats the APK build, six JVM/UI tests, and Paparazzi verification.
- `fledge trust verify --range origin/main..HEAD` passes with expected progressive provenance.
- Exact-head hosted Build, Trust, and CodeQL must pass before the pull request becomes ready.
