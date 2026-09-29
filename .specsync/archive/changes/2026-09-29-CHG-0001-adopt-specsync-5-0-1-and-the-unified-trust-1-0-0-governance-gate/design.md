---
change: CHG-0001-adopt-specsync-5-0-1-and-the-unified-trust-1-0-0-governance-gate
artifact: design
---

# Design

Trust uses the standard profile with 100% contract coverage, blocking risk, progressive provenance, and Trust-managed Atlas disabled. Its lifecycle command is `fledge lanes run verify`, which directly runs build, unit-test, and snapshot tasks without recursively invoking SpecSync. The action is pinned to the immutable Trust 1.0.0 commit. The canonical catalog contract governs every current application source and traces eight durable requirements to native UI and snapshot verification.
