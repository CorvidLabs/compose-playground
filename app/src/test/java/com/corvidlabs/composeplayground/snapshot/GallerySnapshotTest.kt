package com.corvidlabs.composeplayground.snapshot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.corvidlabs.composeplayground.catalog.allComponents
import com.corvidlabs.composeplayground.catalog.componentsByGroup
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.ui.theme.ComposePlaygroundTheme
import org.junit.Rule
import org.junit.Test

/// Renders each component as a CLEAN showcase image (the live demos on cards, no page
/// chrome) plus the home catalog. Run `recordPaparazziDebug` to refresh the PNGs; the docs
/// pipeline trims the whitespace for display on the website and README.
class GallerySnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6.copy(screenHeight = 4000, softButtons = false)
    )

    /// Every component as a tidy showcase of its example demos (light theme).
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

    /// The grouped home catalog (light + dark).
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

/// A clean visual showcase of a component: each example's live demo on its own card with a
/// small caption — no titles, descriptions, or code panels. This is what ships as the image.
@Composable
private fun Showcase(component: Component) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            component.examples.forEach { example ->
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = example.title,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Surface(
                        tonalElevation = 2.dp,
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            example.demo()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomePreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Compose Playground") }) }
        ) { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                componentsByGroup.forEach { (group, members) ->
                    item {
                        Text(
                            text = group.label,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                    items(members) { component ->
                        Surface(
                            shape = MaterialTheme.shapes.large,
                            tonalElevation = 2.dp,
                            modifier = Modifier.fillMaxSize()
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
}
