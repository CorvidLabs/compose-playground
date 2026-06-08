package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SheetsDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Modal bottom sheet") {
            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            var open by remember { mutableStateOf(false) }
            var lastAction by remember { mutableStateOf("Nothing selected") }

            Button(onClick = { open = true }) { Text("Open sheet") }
            Text(lastAction)

            if (open) {
                ModalBottomSheet(
                    onDismissRequest = { open = false },
                    sheetState = sheetState
                ) {
                    Column(modifier = Modifier.navigationBarsPadding()) {
                        Text(
                            "Actions",
                            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                        )
                        SheetAction("Edit", Icons.Filled.Edit) {
                            lastAction = "Edit tapped"
                            scope.launch { sheetState.hide() }.invokeOnCompletion { open = false }
                        }
                        SheetAction("Share", Icons.Filled.Share) {
                            lastAction = "Share tapped"
                            scope.launch { sheetState.hide() }.invokeOnCompletion { open = false }
                        }
                        SheetAction("Delete", Icons.Outlined.Delete) {
                            lastAction = "Delete tapped"
                            scope.launch { sheetState.hide() }.invokeOnCompletion { open = false }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SheetAction(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(label) },
        leadingContent = { Icon(icon, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .clickableRow(onClick)
    )
}

private fun Modifier.clickableRow(onClick: () -> Unit): Modifier =
    this.clickable(onClick = onClick)
