package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PickersDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Inline date picker") {
            val state = rememberDatePickerState()
            Text("Selected: ${formatDate(state.selectedDateMillis)}")
            DatePicker(state = state)
        }

        DemoBlock("Date picker dialog") {
            val state = rememberDatePickerState()
            var showDialog by remember { mutableStateOf(false) }
            var pickedMillis by remember { mutableStateOf<Long?>(null) }

            Text("Picked: ${formatDate(pickedMillis)}")
            Button(onClick = { showDialog = true }) {
                Text("Pick a date")
            }

            if (showDialog) {
                DatePickerDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            pickedMillis = state.selectedDateMillis
                            showDialog = false
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = state)
                }
            }
        }

        DemoBlock("Time picker") {
            val state = rememberTimePickerState(initialHour = 9, initialMinute = 30)
            Text("Selected: ${formatTime(state.hour, state.minute)}")
            TimePicker(state = state)
        }

        DemoBlock("Time input") {
            val state = rememberTimePickerState(initialHour = 14, initialMinute = 15)
            Text("Selected: ${formatTime(state.hour, state.minute)}")
            TimeInput(state = state)
        }
    }
}

private fun formatDate(millis: Long?): String {
    val value = millis ?: return "none"
    val formatter = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
    return formatter.format(Date(value))
}

private fun formatTime(hour: Int, minute: Int): String {
    return String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
}
