---
change: CHG-0005-make-the-specialized-self-hosted-macos-ci-lane-manual-only-so-repositories-witho
artifact: design
---

# Design

Retain the existing macOS job, runner labels, timeout, Fledge lane, local SDK configuration, and
artifact behavior. Remove only the `push` event so `workflow_dispatch` is the sole trigger. Update
the operator comments and pin Checkout and Upload Artifact to immutable v5 commits. No application,
catalog, test, or canonical specification content changes.
