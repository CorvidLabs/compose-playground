// @ts-check
import { defineConfig } from 'astro/config';

// Project GitHub Pages site: https://corvidlabs.github.io/compose-playground/
export default defineConfig({
  site: 'https://corvidlabs.github.io',
  base: '/compose-playground',
  trailingSlash: 'ignore',
  markdown: {
    shikiConfig: { theme: 'css-variables' },
  },
});
