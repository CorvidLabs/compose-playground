---
change: CHG-0002-move-public-pull-request-verification-from-unavailable-self-hosted-macos-runners
artifact: design
---

# Design

Run both required pull-request jobs on `ubuntu-latest`. Set up Temurin JDK 17, Gradle, and Android SDK
35 using immutable action commits. Install platform 35 and build-tools 35.0.0 explicitly. Build the
debug APK with the committed Gradle wrapper and preserve the existing artifact name and path.

Trust retains full checkout history and the immutable Trust 1.0.0 action. The action supplies pinned
Fledge; the hosted toolchain supplies JAVA_HOME and the Android SDK used by the native verification
lane. Workflow concurrency cancels obsolete Trust runs for the same ref. No application behavior,
catalog requirement, snapshot, Pages deployment, Atlas publication, or CodeQL responsibility changes.
