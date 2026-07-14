---
change: CHG-0002-move-public-pull-request-verification-from-unavailable-self-hosted-macos-runners
artifact: testing
---

# Testing

- `actionlint` validates all workflow syntax and expressions when available.
- `specsync check --strict --require-coverage 100 --force` remains at 23/23 files and 5,447/5,447 LOC.
- `specsync agents status` reports Claude, Cursor, Codex, and Gemini installed.
- `fledge lanes run verify` repeats the Android build, six unit/UI tests, and deterministic snapshots.
- Workflow inspection confirms immutable action commits, JDK 17, Android platform 35, the unchanged APK
  artifact, full Trust history, and cancellation of stale Trust runs.
- Exact-head hosted Build and Trust jobs must execute and pass before the pull request becomes ready.
