---
change: CHG-0003-replace-generic-sdd-paths-with-the-complete-repository-specific-delivery-and-gov
artifact: research
---

# Research

The repository's delivery surface consists of the Android application and Gradle wrapper/configuration,
the Astro site, canonical specifications, GitHub workflows, project documentation, Fledge tasks, and
committed Trust, Augur, Attest, Atlas, and SpecSync configuration. Only `.specsync/changes/` is recursive
lifecycle bookkeeping that should be ignored by meaningful-path enforcement.

`NavigationTest` contains three assertions covering home visibility, filtering, and opening one detail.
`GallerySnapshotTest` contains three snapshot methods covering all registered visual representatives,
five dark representatives, and light/dark home views. No current test directly asserts duplicate ids,
unknown-route recovery, DataStore persistence, Android-version-dependent dynamic color, or every
group/example/source invariant. Compilation is not evidence that those behaviors hold.
