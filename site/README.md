# Compose Playground — Docs Site

The [Astro](https://astro.build) documentation site for **Compose Playground**, the
interactive Jetpack Compose + Material 3 component gallery. It renders the component
catalog (descriptions, related links, and source) as a static, browsable site.

**Live:** https://corvidlabs.github.io/compose-playground/

## Development

Run all commands from the `site/` directory.

```bash
bun install   # install dependencies
bun dev       # start the dev server (http://localhost:4321)
bun build     # build the static site to ./dist
bun preview   # preview the production build locally
```

## Base path

The site is served from a GitHub Pages **project** path, so `astro.config.mjs`
sets `base: '/compose-playground'`. All internal links must respect this base —
use Astro's `import.meta.env.BASE_URL` (or the `<base>` it injects) rather than
hard-coding root-relative paths. Locally, the dev server and preview also serve
under `/compose-playground`.

## Data

The component data shown on the site is generated from the Kotlin source. To
regenerate it after changing the catalog, run from the **repo root**:

```bash
python3 site/scripts/extract-components.py
```

## Deployment

Pushing changes under `site/**` (or to the workflow itself) on `main` triggers
[`.github/workflows/pages.yml`](../.github/workflows/pages.yml), which builds with
Bun and deploys to GitHub Pages. The workflow can also be run manually via
`workflow_dispatch`.
