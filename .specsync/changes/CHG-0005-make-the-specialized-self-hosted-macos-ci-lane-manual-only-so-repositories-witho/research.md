---
change: CHG-0005-make-the-specialized-self-hosted-macos-ci-lane-manual-only-so-repositories-witho
artifact: research
---

# Research

The repository runner inventory contains zero self-hosted runners. The queued main-push run is from
`macos-selfhosted.yml`; the GitHub-hosted Build and Trust workflows are independent and already pass
on pushes. The specialized workflow uses only Checkout and Upload Artifact actions, both of which
have known immutable v5 release commits already used by the repository's verified hosted workflow.
