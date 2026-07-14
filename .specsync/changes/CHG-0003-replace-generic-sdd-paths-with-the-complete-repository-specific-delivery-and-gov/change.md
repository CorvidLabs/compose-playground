---
id: CHG-0003-replace-generic-sdd-paths-with-the-complete-repository-specific-delivery-and-gov
state: accepted
type: operations
base_commit: 87f5fc98f927e2f02f75d9d6f448f66ca1b04189
---

# Replace generic SDD paths with the complete repository-specific delivery and governance surface and correct catalog test-evidence overclaims without changing product behavior

## Intent

Replace generic SDD paths with the complete repository-specific delivery and governance surface and correct catalog test-evidence overclaims without changing product behavior

## Affected Canonical Specs

- None

## Acceptance Criteria

- The SDD manifest owns every repository-specific application delivery specification workflow build and governance path while ignoring only recursive lifecycle workspaces; strict lifecycle validation passes at 100 percent; catalog testing documentation distinguishes asserted behavior from build-time or snapshot coverage; no requirement or product behavior changes; hosted success remains unclaimed until the new exact head passes.

## No-spec Rationale

This strengthens lifecycle ownership and corrects verification evidence wording; the eight stable catalog requirements and application behavior do not change.
