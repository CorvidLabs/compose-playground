## ADDED

### REQUIREMENT REQ-catalog-001
Every component SHALL have a unique stable route identifier.

Acceptance Criteria
- Duplicate identifiers fail registry verification.

### REQUIREMENT REQ-catalog-002
The home catalog SHALL group all components in enum order and filter by name, summary, or group label without case sensitivity.

Acceptance Criteria
- A blank query shows grouped entries and a non-blank query shows only matching entries.

### REQUIREMENT REQ-catalog-003
Each example SHALL render its live demo and corresponding source code.

Acceptance Criteria
- The component detail presents the demo and an expandable code panel from the same `CodeExample`.

### REQUIREMENT REQ-catalog-004
Unknown routes SHALL render a recoverable not-found state and unresolved related identifiers SHALL be omitted without crashing.

Acceptance Criteria
- Invalid component identifiers never terminate the application.

### REQUIREMENT REQ-catalog-005
Theme mode and dynamic-color choices SHALL be persisted and restored through the application's single Preferences DataStore.

Acceptance Criteria
- Preference flows emit stored values and update functions persist each supported selection.

### REQUIREMENT REQ-catalog-006
The application theme SHALL honor explicit light or dark selection and SHALL use Material You dynamic colors only on Android 12 or newer when dynamic color is enabled.

Acceptance Criteria
- Static light/dark palettes remain available when dynamic color is disabled or unsupported.

### REQUIREMENT REQ-catalog-007
Every registered component SHALL belong to one declared group and SHALL provide at least one interactive example with corresponding displayed source.

Acceptance Criteria
- Registry validation covers every group file and every `Component` example list.

### REQUIREMENT REQ-catalog-008
Committed gallery snapshots SHALL use deterministic fixtures and SHALL cover the home catalog plus representative light and dark component rendering.

Acceptance Criteria
- Snapshot verification produces the same picker date and month regardless of the execution date.
