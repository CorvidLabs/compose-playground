# Component Gallery

Every one of the **52 components** in the playground, rendered as its detail screen. These are the deterministic Paparazzi snapshots — the real UI, no emulator needed. Each in-app screen also lets you expand the source for every example.

Jump to a group: [Actions](#actions) · [Communication](#communication) · [Containment](#containment) · [Navigation](#navigation) · [Selection & Inputs](#selection--inputs) · [Text & Fields](#text--fields) · [Layout](#layout) · [Lists & Grids](#lists--grids) · [Animation](#animation) · [Gestures & Scroll](#gestures--scroll) · [Graphics & Drawing](#graphics--drawing)


## Actions

### Button

Buttons let people take action. Material 3 ships five emphasis levels — filled (highest), tonal, elevated, outlined, and text (lowest) — plus support for leading icons and disabled states.

<img src="screenshots/button.png" width="300" alt="Button"/>

### Floating Action Button

The FAB represents the primary action of a screen. It comes in small, regular, and large sizes, plus an extended form that pairs an icon with a label.

<img src="screenshots/fab.png" width="300" alt="Floating Action Button"/>

### Icon Button

Icon buttons trigger a single action with a compact, recognizable glyph. They come in standard, filled, and outlined styles, plus a toggleable variant.

<img src="screenshots/icon-button.png" width="300" alt="Icon Button"/>


## Communication

### Badge

A Badge draws attention to a small piece of dynamic status — an unread count or a simple dot — on top of another element. Wrap an anchor in a BadgedBox and supply the badge; an empty Badge renders as a dot, while content renders as a label.

<img src="screenshots/badge.png" width="300" alt="Badge"/>

### Dialog

Dialogs interrupt to request a decision or show critical information. AlertDialog gives you a ready-made layout with icon, title, body, and confirm/dismiss buttons, while the lower-level Dialog lets you place any content — such as a Card — in a modal window.

<img src="screenshots/dialog.png" width="300" alt="Dialog"/>

### Progress Indicators

Progress indicators express an unspecified wait time (indeterminate) or how far along a known operation is (determinate). Both circular and linear variants are available; pass a progress lambda for the determinate form.

<img src="screenshots/progress.png" width="300" alt="Progress Indicators"/>

### Snackbar

A Snackbar shows a short, low-priority message at the bottom of the screen. Host it with a SnackbarHost bound to a SnackbarHostState, then call showSnackbar from a coroutine. An action label lets people respond, and the returned result tells you whether they tapped it.

<img src="screenshots/snackbar.png" width="300" alt="Snackbar"/>

### Tooltip

Tooltips reveal a brief label for an element on long-press or hover. A TooltipBox positions a PlainTooltip relative to its anchor and is driven by a TooltipState that controls when the tooltip appears.

<img src="screenshots/tooltip.png" width="300" alt="Tooltip"/>


## Containment

### Card

Cards hold a single coherent piece of content and actions. Material 3 offers three styles — filled, elevated, and outlined — and cards can be made clickable to act as tappable list items.

<img src="screenshots/card.png" width="300" alt="Card"/>

### Divider

Dividers are thin lines that group content into sections. Use a HorizontalDivider between stacked items and a VerticalDivider inside a fixed-height row to separate side-by-side content.

<img src="screenshots/divider.png" width="300" alt="Divider"/>

### List Item

ListItem renders a single Material 3 list row with slots for headline, supporting text, and leading or trailing content. Wrap a column of them in a tonal Surface and separate them with dividers to build a settings-style list.

<img src="screenshots/list-item.png" width="300" alt="List Item"/>

### Modal Bottom Sheet

A modal bottom sheet presents a focused set of actions anchored to the bottom of the screen, dimming the content behind it. Drive it with rememberModalBottomSheetState and hide it before clearing the open flag for a smooth exit animation.

<img src="screenshots/bottom-sheet.png" width="300" alt="Modal Bottom Sheet"/>


## Navigation

### Bottom App Bar

A bottom app bar groups a small set of action icons at the bottom of the screen and commonly hosts a floating action button for the screen's primary action.

<img src="screenshots/bottom-app-bar.png" width="300" alt="Bottom App Bar"/>

### Menu

Menus display a list of choices on a temporary surface. A DropdownMenu anchors to a trigger such as a button, while an ExposedDropdownMenuBox pairs a text field with a menu so people can pick a value.

<img src="screenshots/menu.png" width="300" alt="Menu"/>

### Navigation Bar

A navigation bar lets people switch between top-level destinations from the bottom of the screen. Each item carries an icon and a label, and exactly one is selected at a time.

<img src="screenshots/navigation-bar.png" width="300" alt="Navigation Bar"/>

### Navigation Drawer

A navigation drawer presents destinations in a vertical sheet that usually slides in from the side. Shown here is the drawer sheet content on its own — a column of NavigationDrawerItem entries — without the full sliding overlay.

<img src="screenshots/navigation-drawer.png" width="300" alt="Navigation Drawer"/>

### Navigation Rail

A navigation rail places top-level destinations along the side of the screen, a common pattern on tablets and other large displays. Like the navigation bar, exactly one destination is selected at a time.

<img src="screenshots/navigation-rail.png" width="300" alt="Navigation Rail"/>

### Tabs

Tabs organize content across parallel sections of a screen. A fixed TabRow splits the available width evenly, while a ScrollableTabRow keeps each tab at its natural width and scrolls horizontally when they overflow.

<img src="screenshots/tabs.png" width="300" alt="Tabs"/>

### Top App Bar

Top app bars display a screen title plus a leading navigation icon and trailing action icons. Material 3 ships small, center-aligned, medium, and large variants; the small and center-aligned forms are shown here.

<img src="screenshots/top-app-bar.png" width="300" alt="Top App Bar"/>


## Selection & Inputs

### Checkbox

Checkboxes let people select one or more items from a set, or turn a single option on or off. A TriStateCheckbox can act as a parent that reflects and toggles a group of child checkboxes.

<img src="screenshots/checkbox.png" width="300" alt="Checkbox"/>

### Chip

Chips help people enter information, make selections, filter content, or trigger actions. Material 3 ships four kinds: assist, filter, input, and suggestion.

<img src="screenshots/chip.png" width="300" alt="Chip"/>

### Date & Time Pickers

Pickers let people choose a date or time. Material 3 provides an inline DatePicker, a DatePickerDialog for modal selection, and a TimePicker with a clock face.

<img src="screenshots/pickers.png" width="300" alt="Date & Time Pickers"/>

### Radio Button

Radio buttons let people select a single option from a mutually exclusive set. Wrap each row in Modifier.selectable so the whole row is tappable and accessible.

<img src="screenshots/radio-button.png" width="300" alt="Radio Button"/>

### Segmented Button

Segmented buttons group related options in a compact toggle. Use single choice for mutually exclusive options and multi choice for independent toggles.

<img src="screenshots/segmented-button.png" width="300" alt="Segmented Button"/>

### Slider

Sliders let people select a value or range from along a track. They support continuous motion, discrete steps within a range, and a two-thumb range variant.

<img src="screenshots/slider.png" width="300" alt="Slider"/>

### Switch

Switches toggle the state of a single item on or off. They are the preferred control for settings rows where the change takes effect immediately.

<img src="screenshots/switch.png" width="300" alt="Switch"/>


## Text & Fields

### Text & Typography

Text renders strings using the Material 3 type scale. It supports rich, inline styling via AnnotatedString, truncation with TextOverflow, and user text selection through SelectionContainer.

<img src="screenshots/text.png" width="300" alt="Text & Typography"/>

### Text Field

Text fields let people enter and edit text. Material 3 offers filled and outlined styles with labels, placeholders, supporting text, error states, leading and trailing icons, keyboard options, and visual transformations.

<img src="screenshots/text-field.png" width="300" alt="Text Field"/>


## Layout

### Box

Box draws its children on top of one another, sized to the largest. Each child positions itself within the Box using Modifier.align, making it ideal for overlays, badges, and corner-anchored content.

<img src="screenshots/box.png" width="300" alt="Box"/>

### Row & Column

Row and Column are the workhorse layouts. Children are placed along the main axis; weight distributes leftover space proportionally, and arrangement controls the gaps between and around them.

<img src="screenshots/row-column.png" width="300" alt="Row & Column"/>

### Spacer

Spacer is an empty layout used to insert gaps. Given a weight inside a Row or Column it absorbs all leftover space, shoving the elements on either side to opposite ends.

<img src="screenshots/spacer.png" width="300" alt="Spacer"/>

### Surface

Surface is the foundational container behind most Material components. It applies a background color, clips to a shape, and raises content with tonal elevation that tints the surface toward the primary color.

<img src="screenshots/surface.png" width="300" alt="Surface"/>


## Lists & Grids

### Lazy Column

LazyColumn lays out and composes only the items that are visible, making it efficient for long or unbounded lists. Pair items with ListItem and HorizontalDivider for a classic settings-style list.

<img src="screenshots/lazy-column.png" width="300" alt="Lazy Column"/>

### Lazy Grid

LazyVerticalGrid arranges items into columns described by GridCells. A GridCells.Fixed value pins the column count, while aspectRatio keeps each cell square.

<img src="screenshots/lazy-grid.png" width="300" alt="Lazy Grid"/>

### Lazy Row

LazyRow is the horizontal counterpart to LazyColumn. It is well suited to carousels of cards or thumbnails, with Arrangement.spacedBy adding consistent gaps between items.

<img src="screenshots/lazy-row.png" width="300" alt="Lazy Row"/>

### Pager

HorizontalPager lays out swipeable pages backed by a PagerState. The state exposes currentPage, which can drive indicator dots beneath the pager.

<img src="screenshots/pager.png" width="300" alt="Pager"/>

### Staggered Grid

LazyVerticalStaggeredGrid packs items of different heights into columns, creating a masonry layout. StaggeredGridCells.Fixed sets the column count.

<img src="screenshots/staggered-grid.png" width="300" alt="Staggered Grid"/>


## Animation

### AnimatedContent

AnimatedContent animates the transition whenever its targetState changes, cross-fading (and optionally sliding) between the old and new content. It is well suited to swapping a number, label, or whole layout in response to state.

<img src="screenshots/animated-content.png" width="300" alt="AnimatedContent"/>

### AnimatedVisibility

AnimatedVisibility animates the appearance and disappearance of its content. Combine enter transitions like fadeIn and expandVertically with matching exits such as fadeOut and shrinkVertically to control how content slides into view.

<img src="screenshots/animated-visibility.png" width="300" alt="AnimatedVisibility"/>

### Crossfade

Crossfade fades between two layouts based on a state value. It is the simplest way to animate a swap between mutually exclusive content, such as paging through a set of cards or switching between loading and loaded states.

<img src="screenshots/crossfade.png" width="300" alt="Crossfade"/>

### InfiniteTransition

rememberInfiniteTransition drives one or more values that animate forever. Pair it with infiniteRepeatable and RepeatMode.Reverse to ping-pong a value back and forth — perfect for pulsing, breathing, or shimmering effects.

<img src="screenshots/infinite-transition.png" width="300" alt="InfiniteTransition"/>

### animate*AsState

The animate*AsState family interpolates a value toward a new target whenever that target changes. Swap in animateColorAsState, animateDpAsState, and friends to drive size, color, offset, or alpha — optionally with a spring for a bouncy feel.

<img src="screenshots/animate-as-state.png" width="300" alt="animate*AsState"/>


## Gestures & Scroll

### Clickable

Modifier.clickable makes any composable respond to taps. combinedClickable adds long-press and double-tap callbacks, letting a plain Box behave like a button.

<img src="screenshots/clickable.png" width="300" alt="Clickable"/>

### Draggable

Modifier.draggable reports drag deltas on a single orientation. Track the accumulated offset in state, clamp it to a range, and apply it with Modifier.offset.

<img src="screenshots/draggable.png" width="300" alt="Draggable"/>

### Pull to Refresh

PullToRefreshBox shows a refresh indicator when the content is pulled past the threshold. Drive it with an isRefreshing flag that you flip off when the work completes.

<img src="screenshots/pull-to-refresh.png" width="300" alt="Pull to Refresh"/>

### Scroll

For a known, modest number of items a plain Column made scrollable with Modifier.verticalScroll(rememberScrollState()) is simpler than a lazy list.

<img src="screenshots/scroll.png" width="300" alt="Scroll"/>

### Swipe to Dismiss

SwipeToDismissBox wraps each row with a swipe gesture and a background that shows behind it. Confirm the dismissal to remove the backing item from the list.

<img src="screenshots/swipe-to-dismiss.png" width="300" alt="Swipe to Dismiss"/>

### Transformable

Modifier.transformable feeds combined pan, zoom, and rotation gestures into a single callback. Store the result in state and render it through a graphicsLayer.

<img src="screenshots/transformable.png" width="300" alt="Transformable"/>


## Graphics & Drawing

### Canvas

Canvas gives you a DrawScope to render custom graphics imperatively. The scope exposes drawLine, drawCircle, drawRect, drawArc, and drawPath, all working in pixels with the composable's own size available as this.size.

<img src="screenshots/canvas.png" width="300" alt="Canvas"/>

### Draw Modifiers

Draw modifiers attach custom rendering to any composable. Modifier.drawBehind paints under the content, Modifier.drawWithContent lets you sequence drawing around drawContent(), and Modifier.border strokes a shaped outline.

<img src="screenshots/draw-modifiers.png" width="300" alt="Draw Modifiers"/>

### Gradients

A Brush describes how to paint an area. Brush.linearGradient interpolates along a vector, Brush.radialGradient fans out from a center, and Brush.sweepGradient sweeps around it. Any brush can fill a composable via Modifier.background.

<img src="screenshots/gradients.png" width="300" alt="Gradients"/>

### Shapes & Clipping

Shapes define an outline used for clipping and backgrounds. RoundedCornerShape softens corners, CircleShape produces a pill or circle, and CutCornerShape bevels them. Apply with Modifier.clip then paint with a background.

<img src="screenshots/shapes.png" width="300" alt="Shapes & Clipping"/>

