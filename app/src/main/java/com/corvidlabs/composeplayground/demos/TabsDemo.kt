package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@Composable
internal fun TabsDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Fixed tabs") {
            val titles = listOf("Home", "Trending", "Saved")
            var selected by remember { mutableIntStateOf(0) }
            Column {
                TabRow(selectedTabIndex = selected) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = selected == index,
                            onClick = { selected = index },
                            text = { Text(title) }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Showing: ${titles[selected]}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        DemoBlock("Scrollable tabs") {
            val titles = listOf("Overview", "Specs", "Reviews", "Q&A", "Shipping", "Returns")
            var selected by remember { mutableIntStateOf(0) }
            Column {
                ScrollableTabRow(selectedTabIndex = selected, edgePadding = 0.dp) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = selected == index,
                            onClick = { selected = index },
                            text = { Text(title) }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(titles[selected], style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
