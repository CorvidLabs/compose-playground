---
change: CHG-0002-move-public-pull-request-verification-from-unavailable-self-hosted-macos-runners
artifact: plan
---

# Plan

1. Replace the unavailable and unsafe pull-request runner labels with hosted Ubuntu.
2. Add immutable Java 17, Gradle, and Android 35 setup to Build and Trust.
3. Preserve full history, APK upload, Trust policy, and independent workflows.
4. Correct visibility and runner narratives without changing the catalog contract.
5. Validate workflow syntax, SpecSync coverage, agent integrations, and native behavior.
6. Push the corrective change, cancel obsolete queued runs, and require exact-head hosted success
   before the pull request leaves draft state.
