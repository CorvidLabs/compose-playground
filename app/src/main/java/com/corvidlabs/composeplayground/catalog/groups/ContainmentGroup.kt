package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup
import kotlinx.coroutines.launch

/// Components in the [ComponentGroup.Containment] group: cards, lists, sheets, and dividers.
internal val containmentComponents: List<Component> get() = listOf(
    cardComponent,
    listItemComponent,
    bottomSheetComponent,
    dividerComponent
)

private val cardComponent = Component(
    id = "card",
    name = "Card",
    group = ComponentGroup.Containment,
    summary = "Filled, elevated, and outlined containers for grouped content",
    description = "Cards hold a single coherent piece of content and actions. Material 3 offers " +
        "three styles — filled, elevated, and outlined — and cards can be made clickable to act " +
        "as tappable list items.",
    docUrl = "https://developer.android.com/jetpack/compose/components/card",
    related = listOf("list-item"),
    examples = listOf(
        CodeExample(
            title = "Styles",
            description = "Filled, elevated, and outlined share the same content slot.",
            code = """
                Card { CardBody("Filled", "A tonal surface that lifts content gently.") }
                ElevatedCard { CardBody("Elevated", "Adds a shadow for extra separation.") }
                OutlinedCard { CardBody("Outlined", "A hairline border instead of a shadow.") }
            """,
            demo = { CardStylesDemo() }
        ),
        CodeExample(
            title = "Clickable card",
            description = "Pass onClick to make the whole card a tappable row.",
            code = """
                Card(onClick = {}) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer) {
                            Icon(Icons.Filled.Person, null, Modifier.padding(8.dp))
                        }
                        Column(Modifier.padding(start = 12.dp)) {
                            Text("Ada Lovelace")
                            Text("Tap to view profile")
                        }
                    }
                }
            """,
            demo = { ClickableCardDemo() }
        )
    )
)

private val listItemComponent = Component(
    id = "list-item",
    name = "List Item",
    group = ComponentGroup.Containment,
    summary = "Headline, supporting text, and leading content rows",
    description = "ListItem renders a single Material 3 list row with slots for headline, " +
        "supporting text, and leading or trailing content. Wrap a column of them in a tonal " +
        "Surface and separate them with dividers to build a settings-style list.",
    docUrl = "https://developer.android.com/jetpack/compose/components/list-item",
    related = listOf("card", "lazy-column"),
    examples = listOf(
        CodeExample(
            title = "List with dividers",
            code = """
                Surface(tonalElevation = 2.dp) {
                    Column {
                        ListItem(
                            headlineContent = { Text("Account") },
                            supportingContent = { Text("Manage your profile") },
                            leadingContent = { Icon(Icons.Filled.AccountCircle, null) }
                        )
                        HorizontalDivider()
                        ListItem(
                            headlineContent = { Text("Settings") },
                            supportingContent = { Text("Theme and notifications") },
                            leadingContent = { Icon(Icons.Filled.Settings, null) }
                        )
                    }
                }
            """,
            demo = { ListItemDemo() }
        )
    )
)

private val bottomSheetComponent = Component(
    id = "bottom-sheet",
    name = "Modal Bottom Sheet",
    group = ComponentGroup.Containment,
    summary = "A sheet that slides up from the bottom edge",
    description = "A modal bottom sheet presents a focused set of actions anchored to the bottom " +
        "of the screen, dimming the content behind it. Drive it with " +
        "rememberModalBottomSheetState and hide it before clearing the open flag for a smooth " +
        "exit animation.",
    docUrl = "https://developer.android.com/jetpack/compose/components/bottom-sheets",
    related = listOf("dialog"),
    examples = listOf(
        CodeExample(
            title = "Open and dismiss",
            description = "A Button opens the sheet; selecting an action animates it closed.",
            code = """
                val sheetState = rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()
                var open by remember { mutableStateOf(false) }

                Button(onClick = { open = true }) { Text("Show actions") }

                if (open) {
                    ModalBottomSheet(onDismissRequest = { open = false }, sheetState = sheetState) {
                        ListItem(
                            headlineContent = { Text("Share") },
                            leadingContent = { Icon(Icons.Filled.Share, null) },
                            modifier = Modifier.clickable {
                                scope.launch { sheetState.hide() }
                                    .invokeOnCompletion { open = false }
                            }
                        )
                    }
                }
            """,
            demo = { BottomSheetDemo() }
        )
    )
)

private val dividerComponent = Component(
    id = "divider",
    name = "Divider",
    group = ComponentGroup.Containment,
    summary = "Horizontal and vertical thin separating lines",
    description = "Dividers are thin lines that group content into sections. Use a " +
        "HorizontalDivider between stacked items and a VerticalDivider inside a fixed-height row " +
        "to separate side-by-side content.",
    docUrl = "https://developer.android.com/jetpack/compose/components/divider",
    related = listOf("list-item"),
    examples = listOf(
        CodeExample(
            title = "Horizontal",
            code = """
                Column {
                    Text("Above the line")
                    HorizontalDivider()
                    Text("Below the line")
                }
            """,
            demo = {
                Column {
                    Text("Above the line")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Below the line")
                }
            }
        ),
        CodeExample(
            title = "Vertical",
            description = "A VerticalDivider needs a bounded height from its parent row.",
            code = """
                Row(modifier = Modifier.height(24.dp)) {
                    Text("Left")
                    VerticalDivider()
                    Text("Right")
                }
            """,
            demo = {
                Row(
                    modifier = Modifier.height(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Left", textAlign = TextAlign.Center)
                    VerticalDivider(modifier = Modifier.padding(horizontal = 12.dp))
                    Text("Right", textAlign = TextAlign.Center)
                }
            }
        )
    )
)

@Composable
private fun CardBody(title: String, body: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = title)
        Text(text = body, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
private fun CardStylesDemo() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) {
            CardBody("Filled", "A tonal surface that lifts content gently.")
        }
        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            CardBody("Elevated", "Adds a shadow for extra separation.")
        }
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            CardBody("Outlined", "A hairline border instead of a shadow.")
        }
    }
}

@Composable
private fun ClickableCardDemo() {
    Card(onClick = {}, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(shape = CircleShape) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(text = "Ada Lovelace")
                Text(text = "Tap to view profile")
            }
        }
    }
}

@Composable
private fun ListItemDemo() {
    Surface(tonalElevation = 2.dp, modifier = Modifier.fillMaxWidth()) {
        Column {
            ListItem(
                headlineContent = { Text("Account") },
                supportingContent = { Text("Manage your profile") },
                leadingContent = { Icon(Icons.Filled.AccountCircle, contentDescription = null) }
            )
            HorizontalDivider()
            ListItem(
                headlineContent = { Text("Settings") },
                supportingContent = { Text("Theme and notifications") },
                leadingContent = { Icon(Icons.Filled.Settings, contentDescription = null) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetDemo() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var open by remember { mutableStateOf(false) }

    Button(onClick = { open = true }) { Text("Show actions") }

    if (open) {
        ModalBottomSheet(onDismissRequest = { open = false }, sheetState = sheetState) {
            ListItem(
                headlineContent = { Text("Share") },
                leadingContent = { Icon(Icons.Filled.Share, contentDescription = null) },
                modifier = Modifier.clickable {
                    scope.launch { sheetState.hide() }.invokeOnCompletion { open = false }
                }
            )
            ListItem(
                headlineContent = { Text("Settings") },
                leadingContent = { Icon(Icons.Filled.Settings, contentDescription = null) },
                modifier = Modifier.clickable {
                    scope.launch { sheetState.hide() }.invokeOnCompletion { open = false }
                }
            )
        }
    }
}
