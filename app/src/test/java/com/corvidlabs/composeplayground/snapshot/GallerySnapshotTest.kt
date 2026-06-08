package com.corvidlabs.composeplayground.snapshot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

/// Renders crisp, tightly-cropped component images (the representative demo on a clean
/// surface) plus a compact home preview. SHRINK fits the image to the content and the high
/// density renders it at many pixels — short, wide images stay sharp at the website's sizes.
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

    /// Every component: its representative (first) example demo, light theme.
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

    /// A few rich showcases in dark theme.
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

    /// A compact home catalog preview (light + dark).
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

/// The component's first example demo on a clean, padded surface — short and wide so it
/// stays sharp when shown on the website.
@Composable
private fun Showcase(component: Component) {
    val example = component.examples.firstOrNull() ?: return
    Surface(color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier
                .widthIn(max = 380.dp)
                .padding(horizontal = 28.dp, vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            example.demo()
        }
    }
}

/// A short, content-sized slice of the home catalog (first three groups) for the hero.
@Composable
private fun HomePreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .widthIn(max = 380.dp)
                .padding(16.dp),
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
