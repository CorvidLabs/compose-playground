package com.corvidlabs.composeplayground.model

import androidx.compose.runtime.Composable

/// Top-level grouping for related components, mirroring the Material 3 catalog structure
/// plus Compose foundation primitives. Drives the section headers on the home screen.
internal enum class ComponentGroup(val label: String, val blurb: String) {
    Actions("Actions", "Buttons, FABs, and other ways to trigger an action"),
    Communication("Communication", "Badges, progress, snackbars, and dialogs"),
    Containment("Containment", "Cards, lists, sheets, and dividers that hold content"),
    Navigation("Navigation", "Bars, rails, drawers, tabs, and menus"),
    Selection("Selection & Inputs", "Checkboxes, switches, sliders, chips, and pickers"),
    TextInputs("Text & Fields", "Typography and text entry"),
    Layout("Layout", "Row, Column, Box, and arrangement primitives"),
    Lists("Lists & Grids", "Lazy lists, grids, and pagers"),
    Animation("Animation", "State, visibility, content, and transitions"),
    Gestures("Gestures & Scroll", "Click, drag, swipe, and scroll handling"),
    Graphics("Graphics & Drawing", "Canvas, brushes, shapes, and clipping")
}

/// A single, self-contained example within a component's detail page.
///
/// Each example pairs a live, interactive [demo] with the exact [code] that produces it,
/// so the detail page can render the component and let the reader expand its source.
internal data class CodeExample(
    val title: String,
    val description: String = "",
    val code: String,
    val demo: @Composable () -> Unit
)

/// A documented component: the unit shown as a row on the home screen and expanded into
/// its own detail page. [summary] feeds search and the list subtitle; [description] is the
/// longer intro on the detail page; [examples] are the scrollable, interactive showcases.
internal data class Component(
    val id: String,
    val name: String,
    val group: ComponentGroup,
    val summary: String,
    val description: String,
    val docUrl: String? = null,
    val related: List<String> = emptyList(),
    val examples: List<CodeExample>
) {
    /// Lowercased haystack used by the home-screen search filter.
    val searchText: String = "$name $summary ${group.label}".lowercase()
}
