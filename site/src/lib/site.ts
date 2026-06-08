import data from "../data/components.json";

export interface Example {
  title: string;
  description: string;
  code: string;
}

export interface Component {
  id: string;
  name: string;
  group: string;
  summary: string;
  description: string;
  docUrl: string | null;
  related: string[];
  examples: Example[];
  screenshot: string;
  sourceFile: string;
}

export interface Group {
  key: string;
  label: string;
  blurb: string;
  componentIds: string[];
}

export interface SiteData {
  meta: {
    repo: string;
    repoUrl: string;
    componentCount: number;
    groupCount: number;
    exampleCount: number;
  };
  groups: Group[];
  components: Component[];
}

export const site = data as SiteData;
export const components = site.components;
export const groups = site.groups;
export const meta = site.meta;

const BASE = import.meta.env.BASE_URL.replace(/\/$/, "");

/** Prefix an absolute-from-root path with the GitHub Pages base. */
export function withBase(path: string): string {
  const clean = path.startsWith("/") ? path : `/${path}`;
  return `${BASE}${clean}`;
}

export function componentById(id: string): Component | undefined {
  return components.find((c) => c.id === id);
}

export function componentsInGroup(groupKey: string): Component[] {
  return components.filter((c) => c.group === groupKey);
}

/** Source file URL on GitHub for a component's group file. */
export function sourceUrl(component: Component): string {
  return `${meta.repoUrl}/blob/main/${component.sourceFile}`;
}

/** A stable accent hue per group, for subtle visual variety. */
export function groupHue(groupKey: string): number {
  const hues: Record<string, number> = {
    Actions: 258,
    Communication: 198,
    Containment: 32,
    Navigation: 280,
    Selection: 160,
    TextInputs: 220,
    Layout: 18,
    Lists: 300,
    Animation: 340,
    Gestures: 120,
    Graphics: 48,
  };
  return hues[groupKey] ?? 258;
}
