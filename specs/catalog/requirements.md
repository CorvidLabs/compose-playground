---
spec: catalog.spec.md
---

## User Stories

- As a developer, I want a searchable component catalog whose examples pair
  rendered Compose UI with the exact source shown to learners.

## Acceptance Criteria

### REQ-catalog-001

Every component SHALL have a unique stable route identifier.

### REQ-catalog-002

The home catalog SHALL group all components in enum order and filter by name,
summary, or group label without case sensitivity.

### REQ-catalog-003

Each example SHALL render its live demo and corresponding source code.

### REQ-catalog-004

Unknown routes SHALL render a recoverable not-found state and unresolved related
identifiers SHALL be omitted without crashing.

## Constraints

- Catalog entries are internal to the Android application module.

## Out of Scope

- Runtime plugin loading and remote catalog mutation.
