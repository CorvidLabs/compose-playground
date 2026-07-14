---
change: CHG-0005-make-the-specialized-self-hosted-macos-ci-lane-manual-only-so-repositories-witho
artifact: context
---

# Context

The specialized macOS workflow triggers on every matching main push even though the repository has
no registered self-hosted runner. Each trigger therefore creates a run that cannot start. The
GitHub-hosted Build and Trust workflows already provide normal push verification, so the macOS lane
should remain available only when an operator deliberately dispatches it after registering a runner.
