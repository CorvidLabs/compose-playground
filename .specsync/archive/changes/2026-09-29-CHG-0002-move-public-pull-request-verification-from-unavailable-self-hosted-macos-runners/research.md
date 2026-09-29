---
change: CHG-0002-move-public-pull-request-verification-from-unavailable-self-hosted-macos-runners
artifact: research
---

# Research

GitHub's repository API reports public visibility and zero assigned self-hosted runners. The queued
Build and Trust jobs have no runner or steps and request only `self-hosted` and `macOS`. The Android
application compiles and targets SDK 35 and requires Java 17. The executable Gradle wrapper is already
committed, while the immutable Trust 1.0.0 action installs its own checksum-pinned Fledge binary.

Therefore the Build job can invoke the Gradle wrapper directly. Trust needs the same Java and Android
setup before its lifecycle step runs. Every added third-party action resolves to an immutable commit.
