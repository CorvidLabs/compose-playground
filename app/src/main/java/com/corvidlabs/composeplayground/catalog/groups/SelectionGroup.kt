package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

/// Components in the [ComponentGroup.Selection] group: checkboxes, switches, sliders,
/// chips, segmented buttons, and pickers for capturing user choices and input.
internal val selectionComponents: List<Component> get() = listOf(
    checkboxComponent,
    switchComponent,
    radioButtonComponent,
    sliderComponent,
    chipComponent,
    segmentedButtonComponent,
    pickersComponent
)

private val checkboxComponent = Component(
    id = "checkbox",
    name = "Checkbox",
    group = ComponentGroup.Selection,
    summary = "Single, grouped, and tri-state parent checkboxes",
    description = "Checkboxes let people select one or more items from a set, or turn a single " +
        "option on or off. A TriStateCheckbox can act as a parent that reflects and toggles a " +
        "group of child checkboxes.",
    docUrl = "https://developer.android.com/jetpack/compose/components/checkbox",
    related = listOf("switch", "radio-button"),
    examples = listOf(
        CodeExample(
            title = "Parent / child group",
            description = "A TriStateCheckbox shows On, Off, or Indeterminate for its children.",
            code = """
                val children = remember { mutableStateListOf(true, false, false) }
                val parentState = when {
                    children.all { it } -> ToggleableState.On
                    children.none { it } -> ToggleableState.Off
                    else -> ToggleableState.Indeterminate
                }
                TriStateCheckbox(
                    state = parentState,
                    onClick = {
                        val target = parentState != ToggleableState.On
                        for (i in children.indices) children[i] = target
                    }
                )
                children.forEachIndexed { index, checked ->
                    Checkbox(checked = checked, onCheckedChange = { children[index] = it })
                }
            """,
            demo = { TriStateCheckboxDemo() }
        )
    )
)

private val switchComponent = Component(
    id = "switch",
    name = "Switch",
    group = ComponentGroup.Selection,
    summary = "Toggle a single setting on or off",
    description = "Switches toggle the state of a single item on or off. They are the preferred " +
        "control for settings rows where the change takes effect immediately.",
    docUrl = "https://developer.android.com/jetpack/compose/components/switch",
    related = listOf("checkbox", "radio-button"),
    examples = listOf(
        CodeExample(
            title = "Settings rows",
            description = "Pair each switch with a label in a Row.",
            code = """
                var wifi by remember { mutableStateOf(true) }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Wi-Fi", modifier = Modifier.weight(1f))
                    Switch(checked = wifi, onCheckedChange = { wifi = it })
                }
            """,
            demo = { SwitchRowsDemo() }
        )
    )
)

private val radioButtonComponent = Component(
    id = "radio-button",
    name = "Radio Button",
    group = ComponentGroup.Selection,
    summary = "Pick exactly one option from a set",
    description = "Radio buttons let people select a single option from a mutually exclusive set. " +
        "Wrap each row in Modifier.selectable so the whole row is tappable and accessible.",
    docUrl = "https://developer.android.com/jetpack/compose/components/radio-button",
    related = listOf("checkbox", "switch"),
    examples = listOf(
        CodeExample(
            title = "Selectable group",
            description = "Make the entire row toggle the selection, not just the dot.",
            code = """
                val options = listOf("Standard", "Priority", "Express")
                var selected by remember { mutableStateOf(options.first()) }
                options.forEach { option ->
                    Row(
                        modifier = Modifier.selectable(
                            selected = option == selected,
                            onClick = { selected = option }
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = option == selected, onClick = null)
                        Text(option)
                    }
                }
            """,
            demo = { RadioGroupDemo() }
        )
    )
)

private val sliderComponent = Component(
    id = "slider",
    name = "Slider",
    group = ComponentGroup.Selection,
    summary = "Continuous, stepped, and range sliders",
    description = "Sliders let people select a value or range from along a track. They support " +
        "continuous motion, discrete steps within a range, and a two-thumb range variant.",
    docUrl = "https://developer.android.com/jetpack/compose/components/slider",
    related = listOf("segmented-button"),
    examples = listOf(
        CodeExample(
            title = "Continuous",
            description = "A free-moving thumb across the default 0..1 range.",
            code = """
                var value by remember { mutableStateOf(0.5f) }
                Slider(value = value, onValueChange = { value = it })
                Text("Value: ${'$'}{(value * 100).roundToInt()}%")
            """,
            demo = { ContinuousSliderDemo() }
        ),
        CodeExample(
            title = "Stepped",
            description = "steps places discrete tick marks inside the value range.",
            code = """
                var value by remember { mutableStateOf(2f) }
                Slider(
                    value = value,
                    onValueChange = { value = it },
                    valueRange = 0f..10f,
                    steps = 9
                )
                Text("Step: ${'$'}{value.roundToInt()}")
            """,
            demo = { SteppedSliderDemo() }
        ),
        CodeExample(
            title = "Range",
            description = "A RangeSlider exposes two thumbs for a lower and upper bound.",
            code = """
                var range by remember { mutableStateOf(20f..80f) }
                RangeSlider(value = range, onValueChange = { range = it }, valueRange = 0f..100f)
                Text("From ${'$'}{range.start.roundToInt()} to ${'$'}{range.endInclusive.roundToInt()}")
            """,
            demo = { RangeSliderDemo() }
        )
    )
)

private val chipComponent = Component(
    id = "chip",
    name = "Chip",
    group = ComponentGroup.Selection,
    summary = "Assist, filter, input, and suggestion chips",
    description = "Chips help people enter information, make selections, filter content, or trigger " +
        "actions. Material 3 ships four kinds: assist, filter, input, and suggestion.",
    docUrl = "https://developer.android.com/jetpack/compose/components/chip",
    related = listOf("button", "segmented-button"),
    examples = listOf(
        CodeExample(
            title = "Assist",
            description = "An AssistChip triggers an action related to nearby content.",
            code = """
                AssistChip(
                    onClick = {},
                    label = { Text("Settings") },
                    leadingIcon = { Icon(Icons.Filled.Settings, null, Modifier.size(18.dp)) }
                )
            """,
            demo = {
                AssistChip(
                    onClick = {},
                    label = { Text("Settings") },
                    leadingIcon = {
                        Icon(Icons.Filled.Settings, contentDescription = null, modifier = Modifier.size(18.dp))
                    }
                )
            }
        ),
        CodeExample(
            title = "Filter (multi-select)",
            description = "FilterChips reflect selection state and can be toggled independently.",
            code = """
                val filters = listOf("Vegetarian", "Vegan", "Gluten-free", "Spicy")
                val selected = remember { mutableStateMapOf<String, Boolean>() }
                FlowRow {
                    filters.forEach { filter ->
                        val isOn = selected[filter] == true
                        FilterChip(
                            selected = isOn,
                            onClick = { selected[filter] = !isOn },
                            label = { Text(filter) },
                            leadingIcon = if (isOn) {
                                { Icon(Icons.Filled.Check, null, Modifier.size(18.dp)) }
                            } else null
                        )
                    }
                }
            """,
            demo = { FilterChipsDemo() }
        ),
        CodeExample(
            title = "Input (removable)",
            description = "An InputChip represents a discrete entry the user can dismiss.",
            code = """
                var visible by remember { mutableStateOf(true) }
                if (visible) {
                    InputChip(
                        selected = false,
                        onClick = {},
                        label = { Text("alice@example.com") },
                        trailingIcon = {
                            Icon(
                                Icons.Filled.Close,
                                "Remove",
                                Modifier.size(18.dp).selectable(false) { visible = false }
                            )
                        }
                    )
                }
            """,
            demo = { InputChipDemo() }
        ),
        CodeExample(
            title = "Suggestion",
            description = "SuggestionChips surface dynamically generated recommendations.",
            code = """
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SuggestionChip(onClick = {}, label = { Text("Sounds good") })
                    SuggestionChip(onClick = {}, label = { Text("On my way") })
                }
            """,
            demo = {
                @OptIn(ExperimentalLayoutApi::class)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SuggestionChip(onClick = {}, label = { Text("Sounds good") })
                    SuggestionChip(onClick = {}, label = { Text("On my way") })
                    SuggestionChip(onClick = {}, label = { Text("Thanks!") })
                }
            }
        )
    )
)

private val segmentedButtonComponent = Component(
    id = "segmented-button",
    name = "Segmented Button",
    group = ComponentGroup.Selection,
    summary = "Single- and multi-choice segmented selection",
    description = "Segmented buttons group related options in a compact toggle. Use single choice " +
        "for mutually exclusive options and multi choice for independent toggles.",
    docUrl = "https://developer.android.com/jetpack/compose/components/segmented-button",
    related = listOf("button", "chip", "slider"),
    examples = listOf(
        CodeExample(
            title = "Single choice",
            description = "Exactly one segment is selected at a time.",
            code = """
                val options = listOf("Day", "Week", "Month")
                var selectedIndex by remember { mutableStateOf(0) }
                SingleChoiceSegmentedButtonRow {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            selected = index == selectedIndex,
                            onClick = { selectedIndex = index },
                            shape = SegmentedButtonDefaults.itemShape(index, options.size)
                        ) { Text(label) }
                    }
                }
            """,
            demo = { SingleChoiceSegmentedDemo() }
        ),
        CodeExample(
            title = "Multi choice",
            description = "Any combination of segments can be active at once.",
            code = """
                val options = listOf("Bold", "Italic", "Underline")
                val checked = remember { mutableStateListOf(false, false, false) }
                MultiChoiceSegmentedButtonRow {
                    options.forEachIndexed { index, label ->
                        SegmentedButton(
                            checked = checked[index],
                            onCheckedChange = { checked[index] = it },
                            shape = SegmentedButtonDefaults.itemShape(index, options.size)
                        ) { Text(label) }
                    }
                }
            """,
            demo = { MultiChoiceSegmentedDemo() }
        )
    )
)

private val pickersComponent = Component(
    id = "pickers",
    name = "Date & Time Pickers",
    group = ComponentGroup.Selection,
    summary = "Inline date picker, date dialog, and time picker",
    description = "Pickers let people choose a date or time. Material 3 provides an inline " +
        "DatePicker, a DatePickerDialog for modal selection, and a TimePicker with a clock face.",
    docUrl = "https://developer.android.com/jetpack/compose/components/datepickers",
    related = listOf("dialog", "switch"),
    examples = listOf(
        CodeExample(
            title = "Inline date picker",
            description = "Embed a DatePicker directly in the layout.",
            code = """
                val state = rememberDatePickerState()
                DatePicker(state = state)
            """,
            demo = { InlineDatePickerDemo() }
        ),
        CodeExample(
            title = "Date picker dialog",
            description = "A Button opens a modal dialog and shows the picked date.",
            code = """
                val state = rememberDatePickerState()
                var open by remember { mutableStateOf(false) }
                Button(onClick = { open = true }) { Text("Pick a date") }
                Text(formatDate(state.selectedDateMillis))
                if (open) {
                    DatePickerDialog(
                        onDismissRequest = { open = false },
                        confirmButton = { TextButton(onClick = { open = false }) { Text("OK") } }
                    ) { DatePicker(state = state) }
                }
            """,
            demo = { DatePickerDialogDemo() }
        ),
        CodeExample(
            title = "Time picker",
            description = "A TimePicker with a clock face reflects the chosen time.",
            code = """
                val state = rememberTimePickerState(initialHour = 9, initialMinute = 30)
                TimePicker(state = state)
                Text(String.format(Locale.US, "%02d:%02d", state.hour, state.minute))
            """,
            demo = { TimePickerDemo() }
        )
    )
)

// MARK: - Stateful demos

@Composable
private fun TriStateCheckboxDemo() {
    val children = remember { mutableStateListOf(true, false, false) }
    val labels = listOf("Sync photos", "Sync videos", "Sync documents")
    val parentState = when {
        children.all { it } -> ToggleableState.On
        children.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    val target = parentState != ToggleableState.On
                    for (i in children.indices) {
                        children[i] = target
                    }
                }
            )
            Text("Sync everything")
        }
        children.forEachIndexed { index, checked ->
            Row(
                modifier = Modifier.padding(start = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = { children[index] = it })
                Text(labels[index])
            }
        }
    }
}

@Composable
private fun SwitchRowsDemo() {
    var wifi by remember { mutableStateOf(true) }
    var bluetooth by remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Wi-Fi", modifier = Modifier.weight(1f))
            Switch(checked = wifi, onCheckedChange = { wifi = it })
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Bluetooth", modifier = Modifier.weight(1f))
            Switch(checked = bluetooth, onCheckedChange = { bluetooth = it })
        }
    }
}

@Composable
private fun RadioGroupDemo() {
    val options = listOf("Standard", "Priority", "Express")
    var selected by remember { mutableStateOf(options.first()) }
    Column {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = option == selected,
                        onClick = { selected = option }
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = option == selected, onClick = null)
                Text(option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Composable
private fun ContinuousSliderDemo() {
    var value by remember { mutableStateOf(0.5f) }
    Column {
        Slider(value = value, onValueChange = { value = it })
        Text("Value: ${(value * 100).roundToInt()}%")
    }
}

@Composable
private fun SteppedSliderDemo() {
    var value by remember { mutableStateOf(2f) }
    Column {
        Slider(
            value = value,
            onValueChange = { value = it },
            valueRange = 0f..10f,
            steps = 9
        )
        Text("Step: ${value.roundToInt()}")
    }
}

@Composable
private fun RangeSliderDemo() {
    var range by remember { mutableStateOf(20f..80f) }
    Column {
        RangeSlider(
            value = range,
            onValueChange = { range = it },
            valueRange = 0f..100f
        )
        Text("From ${range.start.roundToInt()} to ${range.endInclusive.roundToInt()}")
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterChipsDemo() {
    val filters = listOf("Vegetarian", "Vegan", "Gluten-free", "Spicy")
    val selected = remember { mutableStateMapOf<String, Boolean>() }
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        filters.forEach { filter ->
            val isOn = selected[filter] == true
            FilterChip(
                selected = isOn,
                onClick = { selected[filter] = !isOn },
                label = { Text(filter) },
                leadingIcon = if (isOn) {
                    {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                } else {
                    null
                }
            )
        }
    }
}

@Composable
private fun InputChipDemo() {
    var visible by remember { mutableStateOf(true) }
    if (visible) {
        InputChip(
            selected = false,
            onClick = {},
            label = { Text("alice@example.com") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Remove",
                    modifier = Modifier
                        .size(18.dp)
                        .selectable(selected = false, onClick = { visible = false })
                )
            }
        )
    } else {
        AssistChip(
            onClick = { visible = true },
            label = { Text("Restore") },
            leadingIcon = {
                Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(18.dp))
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SingleChoiceSegmentedDemo() {
    val options = listOf("Day", "Week", "Month")
    var selectedIndex by remember { mutableStateOf(0) }
    Column {
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
        Text("Showing: ${options[selectedIndex]}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MultiChoiceSegmentedDemo() {
    val options = listOf("Bold", "Italic", "Underline")
    val checked = remember { mutableStateListOf(false, false, false) }
    Column {
        MultiChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    checked = checked[index],
                    onCheckedChange = { checked[index] = it },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
                ) {
                    Text(label)
                }
            }
        }
        val active = options.filterIndexed { index, _ -> checked[index] }
        Text(if (active.isEmpty()) "No styles" else "Active: ${active.joinToString(", ")}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InlineDatePickerDemo() {
    val state = rememberDatePickerState()
    DatePicker(state = state, title = null)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerDialogDemo() {
    val state = rememberDatePickerState()
    var open by remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = { open = true }) {
            Text("Pick a date")
        }
        Text("Selected: ${formatPickedDate(state.selectedDateMillis)}")
        if (open) {
            DatePickerDialog(
                onDismissRequest = { open = false },
                confirmButton = {
                    TextButton(onClick = { open = false }) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { open = false }) { Text("Cancel") }
                }
            ) {
                DatePicker(state = state)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimePickerDemo() {
    val state = rememberTimePickerState(initialHour = 9, initialMinute = 30)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TimePicker(state = state)
        Text(
            text = String.format(Locale.US, "Picked: %02d:%02d", state.hour, state.minute),
            textAlign = TextAlign.Center
        )
    }
}

/// Formats a date-picker selection in milliseconds into a readable string,
/// returning a placeholder when nothing has been selected yet.
private fun formatPickedDate(millis: Long?): String {
    val value = millis ?: return "none"
    val formatter = SimpleDateFormat("MMM d, yyyy", Locale.US)
    return formatter.format(Date(value))
}
