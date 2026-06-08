package com.corvidlabs.composeplayground.demos

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn
import kotlin.math.roundToInt

@Composable
internal fun ProgressDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Indeterminate") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                CircularProgressIndicator()
                LinearProgressIndicator(modifier = Modifier.weight(1f))
            }
        }

        DemoBlock("Determinate (animated)") {
            var target by remember { mutableStateOf(0.25f) }
            val progress by animateFloatAsState(targetValue = target, label = "progress")
            Text("Progress: ${(progress * 100).roundToInt()}%")
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CircularProgressIndicator(progress = { progress })
                Button(onClick = { target = (target + 0.25f).coerceAtMost(1f) }) { Text("Advance") }
                Button(onClick = { target = 0f }) { Text("Reset") }
            }
        }
    }
}
