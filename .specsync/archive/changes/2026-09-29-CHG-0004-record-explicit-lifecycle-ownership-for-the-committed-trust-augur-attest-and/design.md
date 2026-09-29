---
change: CHG-0004-record-explicit-lifecycle-ownership-for-the-committed-trust-augur-attest-and
artifact: design
---

# Design

Create one no-spec operations change whose affected paths are exactly `.attest.json`, `.augur.toml`,
`.trust.toml`, and `AGENTS.md`. Preserve their committed content and the canonical catalog contract.
Require strict range validation to report no uncovered meaningful paths, then repeat native and hosted
Trust verification before readiness.
