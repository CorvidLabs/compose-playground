---
change: CHG-0003-replace-generic-sdd-paths-with-the-complete-repository-specific-delivery-and-gov
artifact: design
---

# Design

Replace the generic meaningful-path list with exact repository roots and committed configuration files.
Include `app/`, `gradle/`, `site/`, `specs/`, `.github/`, Gradle entry points, both wrapper scripts,
Fledge, documentation, and governance configuration. Ignore only `.specsync/changes/` recursively so
canonical specs and committed SpecSync policy changes require a lifecycle record.

Rewrite only the testing companion's evidence descriptions. Name exact test methods and their observed
assertions, label snapshot/build-only coverage accurately, and enumerate current direct-assertion gaps.
Do not change application code, test code, stable requirement text, or product behavior.
