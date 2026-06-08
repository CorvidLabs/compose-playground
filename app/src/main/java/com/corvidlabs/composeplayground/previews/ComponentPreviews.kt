package com.corvidlabs.composeplayground.previews

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.catalog.componentsByGroup
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup
import com.corvidlabs.composeplayground.ui.components.ExampleCard
import com.corvidlabs.composeplayground.ui.theme.ComposePlaygroundTheme

/// Android Studio preview pane entry points for the catalog. These mirror the detail screen
/// composed by the Paparazzi snapshot harness, but render statically in the IDE so a designer
/// can eyeball any group's first component without launching an emulator. They are compile-time
/// only and never participate in the build's runtime or the snapshot goldens.

// MARK: - Helpers

/// The first component registered under [group], or `null` if the group is empty.
///
/// Pulled from [componentsByGroup] so previews stay in sync as the catalog grows.
private fun firstComponent(group: ComponentGroup): Component? =
    componentsByGroup.firstOrNull { (candidate, _) -> candidate == group }?.second?.firstOrNull()

/// Detail-style screen for a single [component]: a top bar, its description, and a card per
/// example. Mirrors the real detail page so previews are representative.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailPreview(component: Component) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = { TopAppBar(title = { Text(component.name) }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(component.description, style = MaterialTheme.typography.bodyLarge)
                component.examples.forEach { ExampleCard(it) }
            }
        }
    }
}

/// Renders the first component of [group] in a light theme, or nothing if the group is empty.
@Composable
private fun GroupPreview(group: ComponentGroup) {
    val component = firstComponent(group) ?: return
    ComposePlaygroundTheme(darkTheme = false, dynamicColor = false) {
        DetailPreview(component)
    }
}

// MARK: - Per-group previews

@Preview(showBackground = true)
@Composable
private fun ActionsPreview() {
    GroupPreview(ComponentGroup.Actions)
}

@Preview(showBackground = true)
@Composable
private fun CommunicationPreview() {
    GroupPreview(ComponentGroup.Communication)
}

@Preview(showBackground = true)
@Composable
private fun ContainmentPreview() {
    GroupPreview(ComponentGroup.Containment)
}

@Preview(showBackground = true)
@Composable
private fun NavigationPreview() {
    GroupPreview(ComponentGroup.Navigation)
}

@Preview(showBackground = true)
@Composable
private fun SelectionPreview() {
    GroupPreview(ComponentGroup.Selection)
}

@Preview(showBackground = true)
@Composable
private fun TextInputsPreview() {
    GroupPreview(ComponentGroup.TextInputs)
}

@Preview(showBackground = true)
@Composable
private fun LayoutPreview() {
    GroupPreview(ComponentGroup.Layout)
}

@Preview(showBackground = true)
@Composable
private fun ListsPreview() {
    GroupPreview(ComponentGroup.Lists)
}

@Preview(showBackground = true)
@Composable
private fun AnimationPreview() {
    GroupPreview(ComponentGroup.Animation)
}

@Preview(showBackground = true)
@Composable
private fun GesturesPreview() {
    GroupPreview(ComponentGroup.Gestures)
}

@Preview(showBackground = true)
@Composable
private fun GraphicsPreview() {
    GroupPreview(ComponentGroup.Graphics)
}

// MARK: - Light / dark theme comparison

/// A representative screen (the first Containment component) rendered in light theme.
@Preview(name = "Representative - Light", showBackground = true)
@Composable
private fun RepresentativeLightPreview() {
    val component = firstComponent(ComponentGroup.Containment) ?: return
    ComposePlaygroundTheme(darkTheme = false, dynamicColor = false) {
        DetailPreview(component)
    }
}

/// The same representative screen rendered in dark theme.
@Preview(
    name = "Representative - Dark",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun RepresentativeDarkPreview() {
    val component = firstComponent(ComponentGroup.Containment) ?: return
    ComposePlaygroundTheme(darkTheme = true, dynamicColor = false) {
        DetailPreview(component)
    }
}
