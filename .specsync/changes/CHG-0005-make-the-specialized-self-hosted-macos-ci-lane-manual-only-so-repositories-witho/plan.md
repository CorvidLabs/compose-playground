---
change: CHG-0005-make-the-specialized-self-hosted-macos-ci-lane-manual-only-so-repositories-witho
artifact: plan
---

# Plan

1. Remove the automatic main-push trigger while preserving manual dispatch.
2. Correct the workflow comments to describe current runner availability and hosted coverage.
3. Pin every third-party action in the touched workflow to an immutable commit.
4. Run actionlint, strict SpecSync, native Android, and unified Trust verification.
5. Open a draft pull request and promote it only after exact-head hosted checks and review audit pass.
