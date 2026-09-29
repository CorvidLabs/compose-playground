---
change: CHG-0004-record-explicit-lifecycle-ownership-for-the-committed-trust-augur-attest-and
artifact: context
---

# Context

Full repository-specific SDD enforcement correctly made `.attest.json`, `.augur.toml`, `.trust.toml`,
and `AGENTS.md` meaningful. Those files were introduced by the initial rollout, but its recorded
affected paths did not explicitly own them. Hosted Trust therefore completed the native Android lane
and then rejected the contract instead of accepting implicit governance coverage.

This successor records exact ownership without changing any byte or semantic in the four files.
