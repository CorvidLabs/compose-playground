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

/** Inline SVG path markup for a group's icon (Lucide-style, stroke = currentColor). */
export function groupIcon(groupKey: string): string {
  const icons: Record<string, string> = {
    // pointer / tap
    Actions: `<path d="m4 4 7 16 2.3-6.7L20 11 4 4Z"/><path d="m14.5 14.5 5 5"/>`,
    // bell
    Communication: `<path d="M6 8a6 6 0 0 1 12 0c0 7 3 9 3 9H3s3-2 3-9"/><path d="M10.3 21a1.9 1.9 0 0 0 3.4 0"/>`,
    // stacked layers
    Containment: `<path d="m12 2 9 5-9 5-9-5 9-5Z"/><path d="m3 12 9 5 9-5"/><path d="m3 17 9 5 9-5"/>`,
    // compass
    Navigation: `<circle cx="12" cy="12" r="9"/><path d="m16 8-2 6-6 2 2-6 6-2Z"/>`,
    // checked square
    Selection: `<path d="m9 11 3 3 9-9"/><path d="M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"/>`,
    // type "T"
    TextInputs: `<path d="M4 7V5h16v2"/><path d="M12 5v14"/><path d="M9 19h6"/>`,
    // layout grid
    Layout: `<rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/><rect x="3" y="14" width="7" height="7" rx="1"/>`,
    // list
    Lists: `<path d="M8 6h13M8 12h13M8 18h13"/><path d="M3 6h.01M3 12h.01M3 18h.01"/>`,
    // zap / motion
    Animation: `<path d="M13 2 3 14h7l-1 8 10-12h-7l1-8Z"/>`,
    // hand / swipe pointer
    Gestures: `<path d="M8 13V5a1.7 1.7 0 0 1 3.4 0v6"/><path d="M11.4 11V4a1.7 1.7 0 0 1 3.4 0v7"/><path d="M14.8 11.5a1.7 1.7 0 0 1 3.4 0V16a6 6 0 0 1-6 6h-1a6 6 0 0 1-5.2-3L4 15.4a1.7 1.7 0 0 1 2.9-1.7L8 15"/>`,
    // brush / palette
    Graphics: `<circle cx="13.5" cy="6.5" r="1.3"/><circle cx="17" cy="10.5" r="1.3"/><circle cx="8.5" cy="7.5" r="1.3"/><circle cx="6.5" cy="12.5" r="1.3"/><path d="M12 2a10 10 0 0 0 0 20 2.4 2.4 0 0 0 1.9-3.9 2.4 2.4 0 0 1 1.9-3.9H18a4 4 0 0 0 4-4 10 10 0 0 0-10-8.2Z"/>`,
  };
  return icons[groupKey] ?? icons.Actions;
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
