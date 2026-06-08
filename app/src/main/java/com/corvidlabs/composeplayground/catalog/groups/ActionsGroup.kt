package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup

/// Components in the [ComponentGroup.Actions] group: ways to trigger an action.
internal val actionsComponents: List<Component> get() = listOf(
    buttonComponent,
    iconButtonComponent,
    fabComponent
)

private val buttonComponent = Component(
    id = "button",
    name = "Button",
    group = ComponentGroup.Actions,
    summary = "Five emphasis levels from filled to text",
    description = "Buttons let people take action. Material 3 ships five emphasis levels — " +
        "filled (highest), tonal, elevated, outlined, and text (lowest) — plus support for " +
        "leading icons and disabled states.",
    docUrl = "https://developer.android.com/jetpack/compose/components/button",
    related = listOf("icon-button", "fab", "segmented-button"),
    examples = listOf(
        CodeExample(
            title = "Emphasis levels",
            description = "Pick emphasis to match a button's importance on the screen.",
            code = """
                Button(onClick = {}) { Text("Filled") }
                FilledTonalButton(onClick = {}) { Text("Tonal") }
                ElevatedButton(onClick = {}) { Text("Elevated") }
                OutlinedButton(onClick = {}) { Text("Outlined") }
                TextButton(onClick = {}) { Text("Text") }
            """,
            demo = { EmphasisDemo() }
        ),
        CodeExample(
            title = "Leading icon",
            code = """
                Button(onClick = {}) {
                    Icon(Icons.Filled.Add, null, Modifier.size(18.dp))
                    Text("  Add item")
                }
            """,
            demo = {
                Button(onClick = {}) {
                    Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                    Text("  Add item")
                }
            }
        ),
        CodeExample(
            title = "Disabled",
            code = """
                Button(onClick = {}, enabled = false) { Text("Filled") }
                OutlinedButton(onClick = {}, enabled = false) { Text("Outlined") }
            """,
            demo = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {}, enabled = false) { Text("Filled") }
                    OutlinedButton(onClick = {}, enabled = false) { Text("Outlined") }
                }
            }
        )
    )
)

private val iconButtonComponent = Component(
    id = "icon-button",
    name = "Icon Button",
    group = ComponentGroup.Actions,
    summary = "Standard, filled, outlined, and toggle icon buttons",
    description = "Icon buttons trigger a single action with a compact, recognizable glyph. " +
        "They come in standard, filled, and outlined styles, plus a toggleable variant.",
    docUrl = "https://developer.android.com/jetpack/compose/components/icon-button",
    related = listOf("button", "fab"),
    examples = listOf(
        CodeExample(
            title = "Styles",
            code = """
                IconButton(onClick = {}) { Icon(Icons.Filled.Favorite, "Favorite") }
                FilledIconButton(onClick = {}) { Icon(Icons.Filled.Share, "Share") }
                OutlinedIconButton(onClick = {}) { Icon(Icons.Filled.Edit, "Edit") }
            """,
            demo = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(onClick = {}) { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") }
                    FilledIconButton(onClick = {}) { Icon(Icons.Filled.Share, contentDescription = "Share") }
                    OutlinedIconButton(onClick = {}) { Icon(Icons.Filled.Edit, contentDescription = "Edit") }
                }
            }
        ),
        CodeExample(
            title = "Toggle",
            description = "An IconToggleButton flips between checked and unchecked.",
            code = """
                var checked by remember { mutableStateOf(false) }
                IconToggleButton(checked = checked, onCheckedChange = { checked = it }) {
                    Icon(Icons.Filled.Favorite, if (checked) "Liked" else "Not liked")
                }
            """,
            demo = { FavoriteToggle() }
        )
    )
)

private val fabComponent = Component(
    id = "fab",
    name = "Floating Action Button",
    group = ComponentGroup.Actions,
    summary = "Small, regular, large, and extended FABs",
    description = "The FAB represents the primary action of a screen. It comes in small, " +
        "regular, and large sizes, plus an extended form that pairs an icon with a label.",
    docUrl = "https://developer.android.com/jetpack/compose/components/fab",
    related = listOf("button"),
    examples = listOf(
        CodeExample(
            title = "Sizes & extended",
            code = """
                SmallFloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, "Add") }
                FloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, "Add") }
                LargeFloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, "Add") }
                ExtendedFloatingActionButton(
                    onClick = {},
                    icon = { Icon(Icons.Filled.Add, null) },
                    text = { Text("Compose") }
                )
            """,
            demo = { FabSizesDemo() }
        )
    )
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FabSizesDemo() {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SmallFloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, contentDescription = "Add") }
        FloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, contentDescription = "Add") }
        LargeFloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, contentDescription = "Add") }
        ExtendedFloatingActionButton(
            onClick = {},
            icon = { Icon(Icons.Filled.Add, contentDescription = null) },
            text = { Text("Compose") }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EmphasisDemo() {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = {}) { Text("Filled") }
        FilledTonalButton(onClick = {}) { Text("Tonal") }
        ElevatedButton(onClick = {}) { Text("Elevated") }
        OutlinedButton(onClick = {}) { Text("Outlined") }
        TextButton(onClick = {}) { Text("Text") }
    }
}

@Composable
private fun FavoriteToggle() {
    var checked by remember { mutableStateOf(false) }
    IconToggleButton(checked = checked, onCheckedChange = { checked = it }) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = if (checked) "Liked" else "Not liked"
        )
    }
}
