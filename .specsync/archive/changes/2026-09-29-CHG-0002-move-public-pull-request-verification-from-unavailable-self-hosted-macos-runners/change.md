---
id: CHG-0002-move-public-pull-request-verification-from-unavailable-self-hosted-macos-runners
state: archived
type: operations
base_commit: 84d8f2f84f883f26e0672e539afe3cc241fd79c1
---

# Move public pull-request verification from unavailable self-hosted macOS runners to ephemeral hosted Ubuntu with pinned JDK 17, Android 35, and Gradle setup; correct repository visibility narratives and remove empty acceptance boilerplate without changing product behavior

## Intent

Move public pull-request verification from unavailable self-hosted macOS runners to ephemeral hosted Ubuntu with pinned JDK 17, Android 35, and Gradle setup; correct repository visibility narratives and remove empty acceptance boilerplate without changing product behavior

## Affected Canonical Specs

- None

## Acceptance Criteria

- Build and Trust pull-request jobs target ephemeral hosted Ubuntu; both install pinned JDK 17
- Android SDK platform 35
- and Gradle support; Build preserves the debug APK artifact; Trust preserves full-history verification and gains stale-run cancellation; all action dependencies are immutable; repository narratives identify the repository as public; the catalog contract remains unchanged; static workflow validation and native verification pass; hosted success is not claimed before exact-head jobs execute.

## No-spec Rationale

This corrects CI infrastructure, security posture, and documentation only; the catalog product contract and behavior do not change.

## Migration Note

Migrated by hand to SpecSync 6 per Leif's decision (2026-09-28); the 6.0.0 tool refused to archive this legacy record (`` exact-only delivery input `.github/workflows/trust.yml` changed after acceptance and requires an audited reopen; run `specsync change reopen CHG-0002-move-public-pull-request-verification-from-unavailable-self-hosted-macos-runners` to re-verify the accepted change, or supersede it from a later change under a module granted the path by `owns` in `.specsync/config.toml` ``).

- Workflow v1 (SpecSync 5) record, accepted on 2026-07-14 by the closing approval already stored in `approvals.json`. SpecSync 6.0.0 reports its accepted evidence as stale, for the reason quoted above.
- Moved by hand from `.specsync/changes/CHG-0002-move-public-pull-request-verification-from-unavailable-self-hosted-macos-runners/` into the layout `specsync change archive` writes: `accepted-state.json` is the unchanged accepted `state.json`, `state.json` is marked `archived`, and this file's front matter says `archived`.
- `approvals.json`, `verification.json` and every other artifact are the original SpecSync 5 evidence, unchanged. `verification.json` verifies commit `84d8f2f84f883f26e0672e539afe3cc241fd79c1`, not the tree this record was archived from.
- There is no `verification-attempts.json`: SpecSync 5 did not write one for this record, and this migration does not invent attempt history.
- This migration added no verification evidence, test result, attempt history, or approval. It is a manual migration, not a fresh re-verification.
- Closing it through the tool takes `specsync change reopen`, `specsync change verify`, then `specsync change accept`, which writes a new closing approval. Per Leif's decision it was archived by hand instead, so no reopen or new approval is recorded.
