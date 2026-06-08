package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ChipsDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Assist chip") {
            AssistChip(
                onClick = {},
                label = { Text("Get directions") },
                leadingIcon = { Icon(Icons.Filled.Place, contentDescription = null) }
            )
        }

        DemoBlock("Filter chips (multi-select)") {
            val labels = remember { listOf("Kotlin", "Compose", "Material", "Android", "Coroutines") }
            val selected = remember { mutableStateMapOf<String, Boolean>() }
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                labels.forEach { label ->
                    val isOn = selected[label] == true
                    FilterChip(
                        selected = isOn,
                        onClick = { selected[label] = !isOn },
                        label = { Text(label) },
                        leadingIcon = if (isOn) {
                            { Icon(Icons.Filled.Check, contentDescription = null) }
                        } else null
                    )
                }
            }
        }

        DemoBlock("Input chip (removable)") {
            var visible by remember { mutableStateOf(true) }
            if (visible) {
                InputChip(
                    selected = false,
                    onClick = {},
                    label = { Text("alex@example.com") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Remove",
                            modifier = Modifier.clickable { visible = false }
                        )
                    }
                )
            } else {
                Text("Chip removed.")
            }
        }

        DemoBlock("Suggestion chips") {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Today", "Tomorrow", "This week").forEach {
                    SuggestionChip(onClick = {}, label = { Text(it) })
                }
            }
        }
    }
}
