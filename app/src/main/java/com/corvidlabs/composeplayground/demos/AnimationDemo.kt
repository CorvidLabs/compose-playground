package com.corvidlabs.composeplayground.demos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@Composable
internal fun AnimationDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("animate*AsState (color & size)") {
            var toggled by remember { mutableStateOf(false) }
            val color by animateColorAsState(
                targetValue = if (toggled) MaterialTheme.colorScheme.tertiary
                else MaterialTheme.colorScheme.primary,
                label = "color"
            )
            val boxSize by animateDpAsState(
                targetValue = if (toggled) 120.dp else 64.dp,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "size"
            )
            Box(
                modifier = Modifier
                    .size(boxSize)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color)
            )
            Button(onClick = { toggled = !toggled }) { Text("Toggle") }
        }

        DemoBlock("AnimatedVisibility") {
            var visible by remember { mutableStateOf(true) }
            Button(onClick = { visible = !visible }) {
                Text(if (visible) "Hide" else "Show")
            }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Now you see me", color = MaterialTheme.colorScheme.onSecondaryContainer)
                }
            }
        }

        DemoBlock("Crossfade between screens") {
            var page by remember { mutableStateOf(0) }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { page = (page + 1) % 3 }) { Text("Next page") }
                Crossfade(targetState = page, label = "crossfade") { current ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Page ${current + 1}", style = MaterialTheme.typography.headlineSmall)
                    }
                }
            }
        }
    }
}
