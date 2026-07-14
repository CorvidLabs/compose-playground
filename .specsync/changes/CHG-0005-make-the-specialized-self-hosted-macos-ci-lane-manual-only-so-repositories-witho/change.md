---
id: CHG-0005-make-the-specialized-self-hosted-macos-ci-lane-manual-only-so-repositories-witho
state: accepted
type: operations
base_commit: d3476ad90a54d01036bf651b3e44231f3d86766c
---

# Make the specialized self-hosted macOS CI lane manual-only so repositories without registered macOS runners do not queue it on every main push

## Intent

Make the specialized self-hosted macOS CI lane manual-only so repositories without registered macOS runners do not queue it on every main push

## Affected Canonical Specs

- None

## Acceptance Criteria

- The macOS self-hosted workflow exposes workflow_dispatch only and cannot queue from a main push; comments accurately describe manual operation and zero registered runners; every touched action is pinned to an immutable commit; actionlint
- strict SpecSync 100 percent coverage
- native verification
- local Trust
- and exact-head hosted checks pass.

## No-spec Rationale

This changes only CI trigger governance and immutable action references; it does not alter the Android catalog contract or product behavior.
