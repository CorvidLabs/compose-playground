package com.corvidlabs.composeplayground.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode
import com.android.resources.Density
import com.corvidlabs.composeplayground.catalog.allComponents
import com.corvidlabs.composeplayground.catalog.componentsByGroup
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.ui.theme.ComposePlaygroundTheme
import org.junit.Rule
import org.junit.Test

/// Renders crisp, tightly-cropped component images. Most components show their first
/// example demo; transient/interactive components (progress, snackbar, menus, dialogs,
/// sheets, tooltips, pager) use a curated static representation so the image isn't empty.
class GallerySnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6.copy(
            screenHeight = 2400,
            density = Density.XXHIGH,
            xdpi = 480,
            ydpi = 480,
            softButtons = false
        ),
        renderingMode = RenderingMode.SHRINK
    )

    @Test
    fun componentShowcaseLight() {
        allComponents.forEach { component ->
            paparazzi.snapshot(name = component.id) {
                ComposePlaygroundTheme(darkTheme = false, dynamicColor = false) {
                    Showcase(component)
                }
            }
        }
    }

    @Test
    fun componentShowcaseDark() {
        listOf("card", "gradients", "chip", "navigation-bar", "button").forEach { id ->
            val component = component(id)
            paparazzi.snapshot(name = "$id-dark") {
                ComposePlaygroundTheme(darkTheme = true, dynamicColor = false) {
                    Showcase(component)
                }
            }
        }
    }

    @Test
    fun homeCatalog() {
        paparazzi.snapshot(name = "home-light") {
            ComposePlaygroundTheme(darkTheme = false, dynamicColor = false) { HomePreview() }
        }
        paparazzi.snapshot(name = "home-dark") {
            ComposePlaygroundTheme(darkTheme = true, dynamicColor = false) { HomePreview() }
        }
    }

    private fun component(id: String): Component =
        allComponents.firstOrNull { it.id == id } ?: error("Unknown component id: $id")
}

/// The component's representative visual on a clean, padded surface.
@Composable
private fun Showcase(component: Component) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier
                .widthIn(max = 380.dp)
                .padding(horizontal = 28.dp, vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            when (component.id) {
                "progress" -> ProgressShowcase()
                "snackbar" -> SnackbarShowcase()
                "tooltip" -> TooltipShowcase()
                "menu" -> MenuShowcase()
                "bottom-sheet" -> BottomSheetShowcase()
                "dialog" -> DialogShowcase()
                "pager" -> PagerShowcase()
                "pickers" -> PickersShowcase()
                else -> component.examples.firstOrNull()?.demo?.invoke()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PickersShowcase() {
    val fixedDateMillis = 1_780_876_800_000L // 2026-06-08T00:00:00Z
    val state = rememberDatePickerState(
        initialSelectedDateMillis = fixedDateMillis,
        initialDisplayedMonthMillis = fixedDateMillis
    )
    DatePicker(state = state, title = null)
}

@Composable
private fun ProgressShowcase() {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        CircularProgressIndicator(progress = { 0.7f })
        LinearProgressIndicator(progress = { 0.7f }, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun SnackbarShowcase() {
    Snackbar(action = { TextButton(onClick = {}) { Text("Undo") } }) {
        Text("Message archived")
    }
}

@Composable
private fun TooltipShowcase() {
    Surface(
        color = MaterialTheme.colorScheme.inverseSurface,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = "Plain tooltip",
            color = MaterialTheme.colorScheme.inverseOnSurface,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}

@Composable
private fun MenuShowcase() {
    Surface(shape = MaterialTheme.shapes.small, tonalElevation = 3.dp, shadowElevation = 4.dp) {
        Column(modifier = Modifier.width(210.dp).padding(vertical = 8.dp)) {
            listOf("Edit", "Duplicate", "Delete").forEach { label ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }
    }
}

@Composable
private fun BottomSheetShowcase() {
    Surface(
        shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp),
        tonalElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 10.dp)
                    .size(width = 32.dp, height = 4.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant)
            )
            listOf("Edit", "Share", "Delete").forEach { label ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp, vertical = 12.dp)
                )
            }
        }
    }
}

@Composable
private fun DialogShowcase() {
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        tonalElevation = 3.dp,
        shadowElevation = 6.dp,
        modifier = Modifier.width(300.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Delete file?", style = MaterialTheme.typography.titleLarge)
            Text(
                text = "This action can't be undone.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
            ) {
                TextButton(onClick = {}) { Text("Cancel") }
                TextButton(onClick = {}) { Text("Delete") }
            }
        }
    }
}

@Composable
private fun PagerShowcase() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.fillMaxWidth().height(120.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    "Page 1",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(4) { i ->
                Box(
                    modifier = Modifier
                        .size(if (i == 0) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (i == 0) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outlineVariant
                        )
                )
            }
        }
    }
}

@Composable
private fun HomePreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.widthIn(max = 380.dp).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Compose Playground",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            componentsByGroup.take(3).forEach { (group, members) ->
                Text(
                    text = group.label,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 10.dp)
                )
                members.forEach { component ->
                    Surface(
                        shape = MaterialTheme.shapes.large,
                        tonalElevation = 2.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(component.name, style = MaterialTheme.typography.titleMedium)
                            Text(
                                component.summary,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
