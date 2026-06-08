package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ButtonsDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Button emphasis levels") {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {}) { Text("Filled") }
                FilledTonalButton(onClick = {}) { Text("Tonal") }
                ElevatedButton(onClick = {}) { Text("Elevated") }
                OutlinedButton(onClick = {}) { Text("Outlined") }
                TextButton(onClick = {}) { Text("Text") }
            }
        }

        DemoBlock("Button with leading icon") {
            Button(onClick = {}) {
                Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                Text("  Add item")
            }
        }

        DemoBlock("Disabled state") {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {}, enabled = false) { Text("Filled") }
                OutlinedButton(onClick = {}, enabled = false) { Text("Outlined") }
            }
        }

        DemoBlock("Icon buttons") {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = {}) { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") }
                FilledIconButton(onClick = {}) { Icon(Icons.Filled.Share, contentDescription = "Share") }
                OutlinedIconButton(onClick = {}) { Icon(Icons.Filled.Add, contentDescription = "Add") }
                FavoriteToggle()
            }
        }

        DemoBlock("Floating action buttons") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SmallFloatingActionButton(onClick = {}) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
                ExtendedFloatingActionButton(
                    onClick = {},
                    icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                    text = { Text("Compose") }
                )
            }
        }
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
