package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup

/// Components in the [ComponentGroup.Navigation] group: bars, rails, drawers, tabs, and menus.
internal val navigationComponents: List<Component> get() = listOf(
    topAppBarComponent,
    navigationBarComponent,
    navigationRailComponent,
    navigationDrawerComponent,
    tabsComponent,
    menuComponent,
    bottomAppBarComponent
)

private val topAppBarComponent = Component(
    id = "top-app-bar",
    name = "Top App Bar",
    group = ComponentGroup.Navigation,
    summary = "Small and center-aligned bars with navigation and actions",
    description = "Top app bars display a screen title plus a leading navigation icon and " +
        "trailing action icons. Material 3 ships small, center-aligned, medium, and large " +
        "variants; the small and center-aligned forms are shown here.",
    docUrl = "https://developer.android.com/jetpack/compose/components/app-bars",
    related = listOf("bottom-app-bar", "tabs", "navigation-drawer"),
    examples = listOf(
        CodeExample(
            title = "Small top app bar",
            description = "A title aligned to the start, with navigation and action icons.",
            code = """
                TopAppBar(
                    title = { Text("Inbox") },
                    navigationIcon = {
                        IconButton(onClick = {}) { Icon(Icons.Filled.Menu, "Open menu") }
                    },
                    actions = {
                        IconButton(onClick = {}) { Icon(Icons.Filled.Search, "Search") }
                        IconButton(onClick = {}) { Icon(Icons.Filled.MoreVert, "More") }
                    }
                )
            """,
            demo = { SmallTopAppBarDemo() }
        ),
        CodeExample(
            title = "Center-aligned top app bar",
            description = "The title is centered, ideal for top-level screens.",
            code = """
                CenterAlignedTopAppBar(
                    title = { Text("Home") },
                    navigationIcon = {
                        IconButton(onClick = {}) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back") }
                    },
                    actions = {
                        IconButton(onClick = {}) { Icon(Icons.Filled.Settings, "Settings") }
                    }
                )
            """,
            demo = { CenterTopAppBarDemo() }
        )
    )
)

private val navigationBarComponent = Component(
    id = "navigation-bar",
    name = "Navigation Bar",
    group = ComponentGroup.Navigation,
    summary = "Bottom bar with three to five destinations",
    description = "A navigation bar lets people switch between top-level destinations from the " +
        "bottom of the screen. Each item carries an icon and a label, and exactly one is " +
        "selected at a time.",
    docUrl = "https://developer.android.com/jetpack/compose/components/navigation-bar",
    related = listOf("navigation-rail", "navigation-drawer", "bottom-app-bar"),
    examples = listOf(
        CodeExample(
            title = "Four destinations",
            description = "Tap an item to move the selection.",
            code = """
                var selected by remember { mutableIntStateOf(0) }
                NavigationBar {
                    NavigationBarItem(
                        selected = selected == 0,
                        onClick = { selected = 0 },
                        icon = { Icon(Icons.Filled.Home, "Home") },
                        label = { Text("Home") }
                    )
                    // Search, Favorites, Profile ...
                }
            """,
            demo = { NavigationBarDemo() }
        )
    )
)

private val navigationRailComponent = Component(
    id = "navigation-rail",
    name = "Navigation Rail",
    group = ComponentGroup.Navigation,
    summary = "Vertical destination switcher for wide screens",
    description = "A navigation rail places top-level destinations along the side of the screen, " +
        "a common pattern on tablets and other large displays. Like the navigation bar, exactly " +
        "one destination is selected at a time.",
    docUrl = "https://developer.android.com/jetpack/compose/components/navigation-rail",
    related = listOf("navigation-bar", "navigation-drawer"),
    examples = listOf(
        CodeExample(
            title = "Three destinations",
            description = "The rail is anchored to the start edge inside a fixed-height box.",
            code = """
                var selected by remember { mutableIntStateOf(0) }
                NavigationRail {
                    NavigationRailItem(
                        selected = selected == 0,
                        onClick = { selected = 0 },
                        icon = { Icon(Icons.Filled.Home, "Home") },
                        label = { Text("Home") }
                    )
                    // Search, Profile ...
                }
            """,
            demo = { NavigationRailDemo() }
        )
    )
)

private val navigationDrawerComponent = Component(
    id = "navigation-drawer",
    name = "Navigation Drawer",
    group = ComponentGroup.Navigation,
    summary = "A drawer sheet of destination items",
    description = "A navigation drawer presents destinations in a vertical sheet that usually " +
        "slides in from the side. Shown here is the drawer sheet content on its own — a column " +
        "of NavigationDrawerItem entries — without the full sliding overlay.",
    docUrl = "https://developer.android.com/jetpack/compose/components/drawer",
    related = listOf("navigation-bar", "navigation-rail", "top-app-bar"),
    examples = listOf(
        CodeExample(
            title = "Drawer sheet content",
            description = "Each item pairs a label and icon, with one selected.",
            code = """
                ModalDrawerSheet {
                    NavigationDrawerItem(
                        label = { Text("Home") },
                        icon = { Icon(Icons.Filled.Home, null) },
                        selected = true,
                        onClick = {},
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    // Favorites, Profile ...
                }
            """,
            demo = { NavigationDrawerDemo() }
        )
    )
)

private val tabsComponent = Component(
    id = "tabs",
    name = "Tabs",
    group = ComponentGroup.Navigation,
    summary = "Fixed and scrollable tab rows",
    description = "Tabs organize content across parallel sections of a screen. A fixed TabRow " +
        "splits the available width evenly, while a ScrollableTabRow keeps each tab at its " +
        "natural width and scrolls horizontally when they overflow.",
    docUrl = "https://developer.android.com/jetpack/compose/components/tabs",
    related = listOf("top-app-bar", "navigation-bar"),
    examples = listOf(
        CodeExample(
            title = "Fixed tabs",
            description = "Three evenly spaced tabs with content below.",
            code = """
                var selected by remember { mutableIntStateOf(0) }
                val titles = listOf("Overview", "Specs", "Reviews")
                TabRow(selectedTabIndex = selected) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = selected == index,
                            onClick = { selected = index },
                            text = { Text(title) }
                        )
                    }
                }
                Text("Showing: ${'$'}{titles[selected]}")
            """,
            demo = { FixedTabsDemo() }
        ),
        CodeExample(
            title = "Scrollable tabs",
            description = "Many tabs scroll horizontally rather than compressing.",
            code = """
                var selected by remember { mutableIntStateOf(0) }
                val titles = listOf("Daily", "Weekly", "Monthly", "Quarterly", "Yearly")
                ScrollableTabRow(selectedTabIndex = selected) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = selected == index,
                            onClick = { selected = index },
                            text = { Text(title) }
                        )
                    }
                }
                Text("Showing: ${'$'}{titles[selected]}")
            """,
            demo = { ScrollableTabsDemo() }
        )
    )
)

private val menuComponent = Component(
    id = "menu",
    name = "Menu",
    group = ComponentGroup.Navigation,
    summary = "Dropdown menus and exposed dropdown selection",
    description = "Menus display a list of choices on a temporary surface. A DropdownMenu " +
        "anchors to a trigger such as a button, while an ExposedDropdownMenuBox pairs a text " +
        "field with a menu so people can pick a value.",
    docUrl = "https://developer.android.com/jetpack/compose/components/menu",
    related = listOf("button", "tabs"),
    examples = listOf(
        CodeExample(
            title = "Dropdown menu",
            description = "A menu anchored to a button via an expanded flag.",
            code = """
                var expanded by remember { mutableStateOf(false) }
                Box {
                    Button(onClick = { expanded = true }) { Text("Options") }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(text = { Text("Edit") }, onClick = { expanded = false })
                        DropdownMenuItem(text = { Text("Delete") }, onClick = { expanded = false })
                    }
                }
            """,
            demo = { DropdownMenuDemo() }
        ),
        CodeExample(
            title = "Exposed dropdown",
            description = "A read-only text field reveals a menu of selectable values.",
            code = """
                val options = listOf("Low", "Medium", "High")
                var expanded by remember { mutableStateOf(false) }
                var selected by remember { mutableStateOf(options[0]) }
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                    OutlinedTextField(
                        value = selected,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Priority") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = { selected = option; expanded = false }
                            )
                        }
                    }
                }
            """,
            demo = { ExposedDropdownDemo() }
        )
    )
)

private val bottomAppBarComponent = Component(
    id = "bottom-app-bar",
    name = "Bottom App Bar",
    group = ComponentGroup.Navigation,
    summary = "Bottom bar pairing actions with a FAB",
    description = "A bottom app bar groups a small set of action icons at the bottom of the " +
        "screen and commonly hosts a floating action button for the screen's primary action.",
    docUrl = "https://developer.android.com/jetpack/compose/components/app-bars",
    related = listOf("top-app-bar", "navigation-bar", "fab"),
    examples = listOf(
        CodeExample(
            title = "Actions with a FAB",
            description = "Leading action icons plus a trailing floating action button.",
            code = """
                BottomAppBar(
                    actions = {
                        IconButton(onClick = {}) { Icon(Icons.Filled.Search, "Search") }
                        IconButton(onClick = {}) { Icon(Icons.Filled.Favorite, "Favorite") }
                        IconButton(onClick = {}) { Icon(Icons.Filled.Settings, "Settings") }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, "Add") }
                    }
                )
            """,
            demo = { BottomAppBarDemo() }
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SmallTopAppBarDemo() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        TopAppBar(
            title = { Text("Inbox") },
            navigationIcon = {
                IconButton(onClick = {}) { Icon(Icons.Filled.Menu, contentDescription = "Open menu") }
            },
            actions = {
                IconButton(onClick = {}) { Icon(Icons.Filled.Search, contentDescription = "Search") }
                IconButton(onClick = {}) { Icon(Icons.Filled.MoreVert, contentDescription = "More") }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenterTopAppBarDemo() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        CenterAlignedTopAppBar(
            title = { Text("Home") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = {}) { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
            }
        )
    }
}

@Composable
private fun NavigationBarDemo() {
    var selected by remember { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        NavigationBar {
            NavigationBarItem(
                selected = selected == 0,
                onClick = { selected = 0 },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                label = { Text("Home") }
            )
            NavigationBarItem(
                selected = selected == 1,
                onClick = { selected = 1 },
                icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                label = { Text("Search") }
            )
            NavigationBarItem(
                selected = selected == 2,
                onClick = { selected = 2 },
                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
                label = { Text("Favorites") }
            )
            NavigationBarItem(
                selected = selected == 3,
                onClick = { selected = 3 },
                icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                label = { Text("Profile") }
            )
        }
    }
}

@Composable
private fun NavigationRailDemo() {
    var selected by remember { mutableIntStateOf(0) }
    Box(modifier = Modifier.height(220.dp)) {
        NavigationRail {
            NavigationRailItem(
                selected = selected == 0,
                onClick = { selected = 0 },
                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                label = { Text("Home") }
            )
            NavigationRailItem(
                selected = selected == 1,
                onClick = { selected = 1 },
                icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                label = { Text("Search") }
            )
            NavigationRailItem(
                selected = selected == 2,
                onClick = { selected = 2 },
                icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                label = { Text("Profile") }
            )
        }
    }
}

@Composable
private fun NavigationDrawerDemo() {
    var selected by remember { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        ModalDrawerSheet {
            NavigationDrawerItem(
                label = { Text("Home") },
                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                selected = selected == 0,
                onClick = { selected = 0 },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
            NavigationDrawerItem(
                label = { Text("Favorites") },
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                selected = selected == 1,
                onClick = { selected = 1 },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
            NavigationDrawerItem(
                label = { Text("Profile") },
                icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                selected = selected == 2,
                onClick = { selected = 2 },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Composable
private fun FixedTabsDemo() {
    var selected by remember { mutableIntStateOf(0) }
    val titles = listOf("Overview", "Specs", "Reviews")
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
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
            Text(
                text = "Showing: ${titles[selected]}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun ScrollableTabsDemo() {
    var selected by remember { mutableIntStateOf(0) }
    val titles = listOf("Daily", "Weekly", "Monthly", "Quarterly", "Yearly")
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        Column {
            ScrollableTabRow(selectedTabIndex = selected) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = selected == index,
                        onClick = { selected = index },
                        text = { Text(title) }
                    )
                }
            }
            Text(
                text = "Showing: ${titles[selected]}",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun DropdownMenuDemo() {
    var expanded by remember { mutableStateOf(false) }
    Box {
        androidx.compose.material3.Button(onClick = { expanded = true }) { Text("Options") }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("Edit") }, onClick = { expanded = false })
            DropdownMenuItem(text = { Text("Duplicate") }, onClick = { expanded = false })
            DropdownMenuItem(text = { Text("Delete") }, onClick = { expanded = false })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExposedDropdownDemo() {
    val options = listOf("Low", "Medium", "High")
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(options[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text("Priority") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selected = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomAppBarDemo() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        BottomAppBar(
            actions = {
                IconButton(onClick = {}) { Icon(Icons.Filled.Search, contentDescription = "Search") }
                IconButton(onClick = {}) { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") }
                IconButton(onClick = {}) { Icon(Icons.Filled.Settings, contentDescription = "Settings") }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {}) { Icon(Icons.Filled.Add, contentDescription = "Add") }
            }
        )
    }
}
