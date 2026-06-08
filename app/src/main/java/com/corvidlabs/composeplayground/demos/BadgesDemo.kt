package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@Composable
internal fun BadgesDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Dot & count badges") {
            Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                BadgedBox(badge = { Badge() }) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }
                BadgedBox(badge = { Badge { Text("8") } }) {
                    Icon(Icons.Filled.Email, contentDescription = "Mail")
                }
                BadgedBox(badge = { Badge { Text("99+") } }) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
                }
            }
        }

        DemoBlock("Live counter") {
            var count by remember { mutableIntStateOf(0) }
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                BadgedBox(badge = {
                    if (count > 0) Badge { Text("$count") }
                }) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }
                Button(onClick = { count++ }) { Text("Add") }
                Button(onClick = { count = 0 }) { Text("Clear") }
            }
        }
    }
}
