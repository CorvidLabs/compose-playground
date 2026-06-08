package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@Composable
internal fun LayoutDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Row with weights") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WeightBox("1f", 1f, MaterialTheme.colorScheme.primary)
                WeightBox("2f", 2f, MaterialTheme.colorScheme.secondary)
                WeightBox("1f", 1f, MaterialTheme.colorScheme.tertiary)
            }
        }

        DemoBlock("Column arrangement") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Top")
                Text("Middle")
                Text("Bottom")
            }
        }

        DemoBlock("Box stacking & alignment") {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text("TopStart", modifier = Modifier.align(Alignment.TopStart).padding(8.dp))
                Text("Center", modifier = Modifier.align(Alignment.Center))
                Text("BottomEnd", modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp))
            }
        }

        DemoBlock("Spacer") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Left")
                Spacer(modifier = Modifier.weight(1f))
                Text("Right")
            }
        }
    }
}

@Composable
private fun androidx.compose.foundation.layout.RowScope.WeightBox(label: String, weight: Float, color: Color) {
    Box(
        modifier = Modifier
            .weight(weight)
            .size(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(label, color = MaterialTheme.colorScheme.surface)
    }
}
