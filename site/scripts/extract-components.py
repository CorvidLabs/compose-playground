import re, json, glob, textwrap, os

GROUP_META = {
    "Actions": ("Actions", "Buttons, FABs, and other ways to trigger an action"),
    "Communication": ("Communication", "Badges, progress, snackbars, and dialogs"),
    "Containment": ("Containment", "Cards, lists, sheets, and dividers that hold content"),
    "Navigation": ("Navigation", "Bars, rails, drawers, tabs, and menus"),
    "Selection": ("Selection & Inputs", "Checkboxes, switches, sliders, chips, and pickers"),
    "TextInputs": ("Text & Fields", "Typography and text entry"),
    "Layout": ("Layout", "Row, Column, Box, and arrangement primitives"),
    "Lists": ("Lists & Grids", "Lazy lists, grids, and pagers"),
    "Animation": ("Animation", "State, visibility, content, and transitions"),
    "Gestures": ("Gestures & Scroll", "Click, drag, swipe, and scroll handling"),
    "Graphics": ("Graphics & Drawing", "Canvas, brushes, shapes, and clipping"),
}
GROUP_ORDER = list(GROUP_META.keys())

def scan_balanced(s, open_idx):
    """Given index of '(', return index just after the matching ')', skipping strings."""
    depth = 0; i = open_idx; n = len(s)
    while i < n:
        c = s[i]
        if c == '"':
            if s[i:i+3] == '"""':
                end = s.index('"""', i+3); i = end + 3; continue
            i += 1
            while i < n and s[i] != '"':
                i += 2 if s[i] == '\\' else 1
            i += 1; continue
        if c == '(':
            depth += 1
        elif c == ')':
            depth -= 1
            if depth == 0:
                return i + 1
        i += 1
    return -1

def join_quoted(blob):
    parts = re.findall(r'"((?:[^"\\]|\\.)*)"', blob)
    s = " ".join(p for p in parts) if False else "".join(parts)
    return re.sub(r'\s+', ' ', s.replace('\\"', '"')).strip()

def clean_code(raw):
    raw = raw.replace("${'$'}", "$")
    raw = raw.strip("\n")
    raw = textwrap.dedent(raw)
    return raw.rstrip()

def parse_example(block):
    # title
    mt = re.search(r'title\s*=\s*"((?:[^"\\]|\\.)*)"', block)
    title = mt.group(1) if mt else ""
    # code
    mc = re.search(r'code\s*=\s*"""(.*?)"""', block, re.S)
    code = clean_code(mc.group(1)) if mc else ""
    # description: a `description = "..."` that appears BEFORE `code =`
    head = block[:mc.start()] if mc else block
    md = re.search(r'description\s*=\s*"((?:[^"\\]|\\.)*)"', head)
    desc = md.group(1).replace('\\"', '"') if md else ""
    return {"title": title, "description": desc, "code": code}

def parse_component(block):
    comp = {}
    mid = re.search(r'id\s*=\s*"([a-z0-9-]+)"', block)
    mname = re.search(r'name\s*=\s*"([^"]+)"', block)
    mgroup = re.search(r'group\s*=\s*ComponentGroup\.(\w+)', block)
    msum = re.search(r'summary\s*=\s*"((?:[^"\\]|\\.)*)"', block)
    if not (mid and mname and mgroup): return None
    comp["id"] = mid.group(1)
    comp["name"] = mname.group(1)
    comp["group"] = mgroup.group(1)
    comp["summary"] = msum.group(1).replace('\\"', '"') if msum else ""
    # description: between `description =` and next field
    md = re.search(r'description\s*=\s*(.*?)\n\s*(?:docUrl|related|examples)\s*=', block, re.S)
    comp["description"] = join_quoted(md.group(1)) if md else ""
    mdoc = re.search(r'docUrl\s*=\s*"([^"]+)"', block)
    comp["docUrl"] = mdoc.group(1) if mdoc else None
    mrel = re.search(r'related\s*=\s*listOf\(([^)]*)\)', block)
    comp["related"] = re.findall(r'"([a-z0-9-]+)"', mrel.group(1)) if mrel else []
    # examples
    examples = []
    ex_start = block.find("examples")
    region = block[ex_start:] if ex_start != -1 else ""
    idx = 0
    while True:
        m = re.search(r'CodeExample\s*\(', region[idx:])
        if not m: break
        paren = idx + m.end() - 1
        end = scan_balanced(region, paren)
        if end == -1: break
        examples.append(parse_example(region[paren:end]))
        idx = end
    comp["examples"] = examples
    return comp

components = []
for f in sorted(glob.glob("app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/*.kt")):
    s = open(f).read()
    idx = 0
    while True:
        m = re.search(r'=\s*Component\s*\(', s[idx:])
        if not m: break
        paren = idx + m.end() - 1
        end = scan_balanced(s, paren)
        if end == -1: break
        c = parse_component(s[paren:end])
        if c:
            c["screenshot"] = f"screenshots/{c['id']}.png"
            c["sourceFile"] = "app/src/main/java/com/corvidlabs/composeplayground/catalog/groups/" + os.path.basename(f)
            components.append(c)
        idx = end

# order by group then keep file order within group
order_index = {g: i for i, g in enumerate(GROUP_ORDER)}
components.sort(key=lambda c: order_index.get(c["group"], 99))

groups = []
for g in GROUP_ORDER:
    items = [c for c in components if c["group"] == g]
    if not items: continue
    label, blurb = GROUP_META[g]
    groups.append({"key": g, "label": label, "blurb": blurb, "componentIds": [c["id"] for c in items]})

data = {
    "meta": {
        "repo": "CorvidLabs/compose-playground",
        "repoUrl": "https://github.com/CorvidLabs/compose-playground",
        "componentCount": len(components),
        "groupCount": len(groups),
        "exampleCount": sum(len(c["examples"]) for c in components),
    },
    "groups": groups,
    "components": components,
}
os.makedirs("site/src/data", exist_ok=True)
json.dump(data, open("site/src/data/components.json", "w"), indent=2, ensure_ascii=False)
print(f"components={len(components)} groups={len(groups)} examples={data['meta']['exampleCount']}")
# sanity: any component with 0 examples or empty code?
noex = [c['id'] for c in components if not c['examples']]
emptycode = [(c['id'], e['title']) for c in components for e in c['examples'] if not e['code'].strip()]
print("components with 0 examples:", noex)
print("examples with empty code:", emptycode[:10], "count:", len(emptycode))
