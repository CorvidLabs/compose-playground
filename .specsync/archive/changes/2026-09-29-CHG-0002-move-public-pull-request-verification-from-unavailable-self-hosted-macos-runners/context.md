---
change: CHG-0002-move-public-pull-request-verification-from-unavailable-self-hosted-macos-runners
artifact: context
---

# Context

The repository is public, but its pull-request Build and Trust jobs describe it as private and request
`self-hosted` plus `macOS` labels. GitHub reports no assigned repository runners, so both exact-head
jobs remain queued without starting a step. Attaching a persistent self-hosted runner would also let
untrusted fork pull requests execute repository-controlled Gradle and Fledge commands on that host.

The safe correction is to use ephemeral GitHub-hosted Ubuntu runners with the repository's required
JDK 17 and Android SDK 35 toolchain. The debug APK artifact, complete Trust lifecycle lane, CodeQL,
Pages, and standalone Atlas responsibilities remain unchanged.
