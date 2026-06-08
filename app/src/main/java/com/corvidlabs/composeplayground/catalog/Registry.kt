package com.corvidlabs.composeplayground.catalog

import com.corvidlabs.composeplayground.catalog.groups.actionsComponents
import com.corvidlabs.composeplayground.catalog.groups.animationComponents
import com.corvidlabs.composeplayground.catalog.groups.communicationComponents
import com.corvidlabs.composeplayground.catalog.groups.containmentComponents
import com.corvidlabs.composeplayground.catalog.groups.gesturesComponents
import com.corvidlabs.composeplayground.catalog.groups.graphicsComponents
import com.corvidlabs.composeplayground.catalog.groups.layoutComponents
import com.corvidlabs.composeplayground.catalog.groups.listsComponents
import com.corvidlabs.composeplayground.catalog.groups.navigationComponents
import com.corvidlabs.composeplayground.catalog.groups.selectionComponents
import com.corvidlabs.composeplayground.catalog.groups.textComponents
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup

/// Flat registry of every documented component, assembled from the per-group files.
internal val allComponents: List<Component> =
    actionsComponents +
        communicationComponents +
        containmentComponents +
        navigationComponents +
        selectionComponents +
        textComponents +
        layoutComponents +
        listsComponents +
        animationComponents +
        gesturesComponents +
        graphicsComponents

/// Components bucketed by [ComponentGroup], preserving group declaration order and the
/// order within each group. Powers the sectioned home screen.
internal val componentsByGroup: List<Pair<ComponentGroup, List<Component>>> =
    ComponentGroup.entries
        .map { group -> group to allComponents.filter { it.group == group } }
        .filter { (_, items) -> items.isNotEmpty() }

/// Resolves a component by its stable [id] (used as the navigation route key).
internal fun componentFor(id: String?): Component? = allComponents.firstOrNull { it.id == id }
