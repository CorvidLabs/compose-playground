---
change: CHG-0003-replace-generic-sdd-paths-with-the-complete-repository-specific-delivery-and-gov
artifact: context
---

# Context

The adopted SDD manifest still contains generic paths for unrelated Rust, Swift, Python, Go, and
JavaScript repositories. It separately appends a subset of Android paths, ignores the entire canonical
`specs/` tree, and ignores all of `.specsync/`, including committed policy configuration. That does not
represent full repository-specific lifecycle ownership.

The canonical testing companion also attributes requirements to broad build or snapshot execution
without identifying what the tests actually assert. The correction must distinguish direct behavioral
assertions from compilation and visual smoke coverage without weakening or inventing requirements.
