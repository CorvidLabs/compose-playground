package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import com.corvidlabs.composeplayground.ui.DemoColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppBarsDemo(padding: PaddingValues) {
    DemoColumn(padding) {
        DemoBlock("Top app bar") {
            Surface(
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = { Text("Inbox") },
                    navigationIcon = {
                        IconButton(onClick = {}) { Icon(Icons.Filled.Home, contentDescription = null) }
                    },
                    actions = {
                        IconButton(onClick = {}) { Icon(Icons.Filled.Search, contentDescription = "Search") }
                        IconButton(onClick = {}) { Icon(Icons.Filled.MoreVert, contentDescription = "More") }
                    }
                )
            }
        }

        DemoBlock("Center-aligned top app bar") {
            Surface(
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                CenterAlignedTopAppBar(
                    title = { Text("Profile") },
                    actions = {
                        IconButton(onClick = {}) { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
                    }
                )
            }
        }

        DemoBlock("Navigation bar") {
            data class Dest(val label: String, val icon: ImageVector)
            val destinations = listOf(
                Dest("Home", Icons.Filled.Home),
                Dest("Likes", Icons.Filled.Favorite),
                Dest("Search", Icons.Filled.Search),
                Dest("Profile", Icons.Filled.Person)
            )
            var selected by remember { mutableIntStateOf(0) }
            Surface(
                shape = RoundedCornerShape(12.dp),
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationBar {
                    destinations.forEachIndexed { index, dest ->
                        NavigationBarItem(
                            selected = selected == index,
                            onClick = { selected = index },
                            icon = { Icon(dest.icon, contentDescription = dest.label) },
                            label = { Text(dest.label) }
                        )
                    }
                }
            }
        }
    }
}
