package com.corvidlabs.composeplayground.snapshot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.corvidlabs.composeplayground.catalog.allComponents
import com.corvidlabs.composeplayground.catalog.componentsByGroup
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.ui.components.ExampleCard
import com.corvidlabs.composeplayground.ui.theme.ComposePlaygroundTheme
import org.junit.Rule
import org.junit.Test

/// Renders the catalog to deterministic PNGs (JVM, no device). Run `recordPaparazziDebug`
/// to refresh the images, `verifyPaparazziDebug` to assert no visual regressions.
class GallerySnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_6.copy(softButtons = false))

    /// One flagship component per group, captured as its detail screen in light theme.
    @Test
    fun componentScreensLight() {
        flagshipIds.forEach { id ->
            val component = component(id)
            paparazzi.snapshot(name = id) {
                ComposePlaygroundTheme(darkTheme = false, dynamicColor = false) {
                    DetailPreview(component)
                }
            }
        }
    }

    /// A few visually rich screens captured in dark theme.
    @Test
    fun componentScreensDark() {
        listOf("card", "gradients", "chip").forEach { id ->
            val component = component(id)
            paparazzi.snapshot(name = "$id-dark") {
                ComposePlaygroundTheme(darkTheme = true, dynamicColor = false) {
                    DetailPreview(component)
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

    private companion object {
        val flagshipIds = listOf(
            "button", "icon-button", "progress", "card", "list-item",
            "navigation-bar", "tabs", "chip", "slider", "pickers",
            "text-field", "row-column", "lazy-grid", "pager",
            "animate-as-state", "draggable", "gradients", "canvas"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailPreview(component: Component) {
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
            component.examples.take(2).forEach { ExampleCard(it) }
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
