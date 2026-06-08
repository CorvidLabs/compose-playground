package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@Composable
internal fun SelectionDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Checkboxes") {
            val items = remember { listOf("Apples", "Bananas", "Cherries") }
            val checked = remember { mutableStateListOfBooleans(items.size) }
            Column {
                items.forEachIndexed { index, label ->
                    LabeledRow(label) {
                        Checkbox(
                            checked = checked[index],
                            onCheckedChange = { checked[index] = it }
                        )
                    }
                }
            }
        }

        DemoBlock("Tri-state (parent / children)") {
            val children = remember { mutableStateListOfBooleans(3) }
            val parentState = when {
                children.all { it } -> ToggleableState.On
                children.none { it } -> ToggleableState.Off
                else -> ToggleableState.Indeterminate
            }
            LabeledRow("Select all") {
                TriStateCheckbox(
                    state = parentState,
                    onClick = {
                        val target = parentState != ToggleableState.On
                        for (i in children.indices) children[i] = target
                    }
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                repeat(3) { i ->
                    LabeledRow("  Option ${i + 1}") {
                        Checkbox(checked = children[i], onCheckedChange = { children[i] = it })
                    }
                }
            }
        }

        DemoBlock("Switches") {
            var wifi by remember { mutableStateOf(true) }
            var bluetooth by remember { mutableStateOf(false) }
            LabeledRow("Wi-Fi") { Switch(checked = wifi, onCheckedChange = { wifi = it }) }
            LabeledRow("Bluetooth") { Switch(checked = bluetooth, onCheckedChange = { bluetooth = it }) }
        }

        DemoBlock("Radio group") {
            val options = listOf("Low", "Medium", "High")
            var selected by remember { mutableStateOf(options[1]) }
            Column {
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = option == selected,
                                onClick = { selected = option }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        RadioButton(selected = option == selected, onClick = { selected = option })
                        Text(option)
                    }
                }
            }
        }
    }
}

@Composable
private fun LabeledRow(label: String, control: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        control()
        Text(label)
    }
}

private fun mutableStateListOfBooleans(size: Int) =
    androidx.compose.runtime.mutableStateListOf<Boolean>().apply { repeat(size) { add(false) } }
