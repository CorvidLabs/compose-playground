package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SegmentedButtonsDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Single choice") {
            val options = listOf("Day", "Week", "Month")
            var selectedIndex by remember { mutableStateOf(1) }

            Text("Range: ${options[selectedIndex]}")
            SingleChoiceSegmentedButtonRow {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        selected = index == selectedIndex,
                        onClick = { selectedIndex = index },
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
                    ) {
                        Text(label)
                    }
                }
            }
        }

        DemoBlock("Multi choice") {
            val options = listOf("Bold", "Italic", "Underline")
            val selected = remember { mutableStateListOf(0) }

            val summary = if (selected.isEmpty()) {
                "none"
            } else {
                selected.sorted().joinToString(", ") { options[it] }
            }
            Text("Style: $summary")
            MultiChoiceSegmentedButtonRow {
                options.forEachIndexed { index, label ->
                    SegmentedButton(
                        checked = selected.contains(index),
                        onCheckedChange = {
                            if (selected.contains(index)) {
                                selected.remove(index)
                            } else {
                                selected.add(index)
                            }
                        },
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
                    ) {
                        Text(label)
                    }
                }
            }
        }
    }
}
