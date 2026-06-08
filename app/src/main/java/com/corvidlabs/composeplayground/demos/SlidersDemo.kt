package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn
import kotlin.math.roundToInt

@Composable
internal fun SlidersDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Continuous slider") {
            var value by remember { mutableStateOf(0.4f) }
            Text("Volume: ${(value * 100).roundToInt()}%")
            Slider(value = value, onValueChange = { value = it })
        }

        DemoBlock("Stepped slider") {
            var value by remember { mutableStateOf(3f) }
            Text("Rating: ${value.roundToInt()} / 5")
            Slider(
                value = value,
                onValueChange = { value = it },
                valueRange = 0f..5f,
                steps = 4
            )
        }

        DemoBlock("Range slider") {
            var range by remember { mutableStateOf(20f..80f) }
            Text("Price: \$${range.start.roundToInt()} – \$${range.endInclusive.roundToInt()}")
            RangeSlider(
                value = range,
                onValueChange = { range = it },
                valueRange = 0f..100f
            )
        }
    }
}
