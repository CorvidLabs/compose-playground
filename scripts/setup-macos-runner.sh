#!/usr/bin/env bash
# Register this Mac as a self-hosted GitHub Actions runner for the repo.
#
# Prereqs on this machine (already true on the CorvidLabs dev Mac):
#   - Android SDK at ~/Library/Android/sdk
#   - Android Studio (provides the JBR used by ./gradlew)
#   - fledge on PATH
#
# Usage:
#   ./scripts/setup-macos-runner.sh
#
# It fetches a short-lived registration token via the gh CLI (needs `gh auth login`
# with repo admin), downloads the runner into ./.runner, and configures it with the
# labels the workflow expects: self-hosted, macOS.

set -euo pipefail

REPO="CorvidLabs/compose-playground"
RUNNER_DIR="${RUNNER_DIR:-$HOME/actions-runner-compose-playground}"
VERSION="${RUNNER_VERSION:-2.321.0}"
ARCH="$([ "$(uname -m)" = "arm64" ] && echo arm64 || echo x64)"
PKG="actions-runner-osx-${ARCH}-${VERSION}.tar.gz"

echo "Repo:        $REPO"
echo "Runner dir:  $RUNNER_DIR"
echo "Package:     $PKG"

mkdir -p "$RUNNER_DIR"
cd "$RUNNER_DIR"

if [ ! -f ./config.sh ]; then
  echo "Downloading runner $VERSION ($ARCH)…"
  curl -fsSL -o "$PKG" \
    "https://github.com/actions/runner/releases/download/v${VERSION}/${PKG}"
  tar xzf "$PKG"
fi

echo "Requesting a registration token via gh…"
TOKEN="$(gh api -X POST "repos/${REPO}/actions/runners/registration-token" -q .token)"

./config.sh \
  --url "https://github.com/${REPO}" \
  --token "$TOKEN" \
  --labels "self-hosted,macOS" \
  --name "$(hostname -s)-compose-playground" \
  --unattended \
  --replace

echo
echo "Configured. Start the runner with:"
echo "  cd \"$RUNNER_DIR\" && ./run.sh"
echo "Or install it as a login service:"
echo "  cd \"$RUNNER_DIR\" && ./svc.sh install && ./svc.sh start"
