---
spec: catalog.spec.md
---

## Context

The playground needs one discoverable registry that drives navigation, search,
examples, screenshots, and the generated companion site.

## Related Modules

- Per-group catalog declarations and the static Astro component index.

## Design Decisions

- Group files own entries while the registry aggregates them.
- Related links are identifier based and tolerate missing targets.
