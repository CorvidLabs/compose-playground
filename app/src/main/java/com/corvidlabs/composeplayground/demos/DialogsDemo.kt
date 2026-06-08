package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@Composable
internal fun DialogsDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Alert dialog") {
            var open by remember { mutableStateOf(false) }
            var result by remember { mutableStateOf("No choice yet") }
            Button(onClick = { open = true }) { Text("Delete account") }
            Text(result, style = MaterialTheme.typography.bodyMedium)
            if (open) {
                AlertDialog(
                    onDismissRequest = { open = false },
                    icon = { Icon(Icons.Filled.Warning, contentDescription = null) },
                    title = { Text("Delete account?") },
                    text = { Text("This action is permanent and cannot be undone.") },
                    confirmButton = {
                        TextButton(onClick = { open = false; result = "Confirmed" }) { Text("Delete") }
                    },
                    dismissButton = {
                        TextButton(onClick = { open = false; result = "Cancelled" }) { Text("Cancel") }
                    }
                )
            }
        }

        DemoBlock("Custom dialog") {
            var open by remember { mutableStateOf(false) }
            OutlinedButton(onClick = { open = true }) { Text("Show custom dialog") }
            if (open) {
                Dialog(onDismissRequest = { open = false }) {
                    Card {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Fully custom content", style = MaterialTheme.typography.titleLarge)
                            Text("A Dialog lets you place any composable inside a floating window.")
                            Button(onClick = { open = false }) { Text("Got it") }
                        }
                    }
                }
            }
        }
    }
}
