package com.corvidlabs.composeplayground

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Animation
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SmartButton
import androidx.compose.material.icons.outlined.Tab
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.outlined.VerticalSplit
import androidx.compose.material.icons.outlined.ViewAgenda
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.corvidlabs.composeplayground.demos.AnimationDemo
import com.corvidlabs.composeplayground.demos.AppBarsDemo
import com.corvidlabs.composeplayground.demos.BadgesDemo
import com.corvidlabs.composeplayground.demos.ButtonsDemo
import com.corvidlabs.composeplayground.demos.CardsDemo
import com.corvidlabs.composeplayground.demos.ChipsDemo
import com.corvidlabs.composeplayground.demos.DialogsDemo
import com.corvidlabs.composeplayground.demos.LayoutDemo
import com.corvidlabs.composeplayground.demos.ListsDemo
import com.corvidlabs.composeplayground.demos.ProgressDemo
import com.corvidlabs.composeplayground.demos.SelectionDemo
import com.corvidlabs.composeplayground.demos.SheetsDemo
import com.corvidlabs.composeplayground.demos.SlidersDemo
import com.corvidlabs.composeplayground.demos.SnackbarDemo
import com.corvidlabs.composeplayground.demos.TabsDemo
import com.corvidlabs.composeplayground.demos.TextDemo
import com.corvidlabs.composeplayground.demos.TextFieldsDemo

/// A single entry in the playground catalog.
///
/// Each demo owns a stable [route] used for navigation and a [content] composable
/// that renders the showcase. The content receives the scaffold inner padding so it
/// can lay out edge-to-edge beneath the top app bar.
internal data class Demo(
    val route: String,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val content: @Composable (PaddingValues) -> Unit
)

/// The full set of component showcases, in display order.
internal val demos: List<Demo> = listOf(
    Demo("buttons", "Buttons", "Filled, tonal, outlined, FABs", Icons.Outlined.SmartButton) { ButtonsDemo(it) },
    Demo("text", "Text & Typography", "Type scale, spans, selection", Icons.Outlined.TextFields) { TextDemo(it) },
    Demo("textfields", "Text Fields", "Filled, outlined, password, search", Icons.Outlined.Edit) { TextFieldsDemo(it) },
    Demo("selection", "Selection Controls", "Checkbox, switch, radio", Icons.Outlined.CheckCircle) { SelectionDemo(it) },
    Demo("sliders", "Sliders", "Continuous, stepped, range", Icons.Outlined.Tune) { SlidersDemo(it) },
    Demo("chips", "Chips", "Assist, filter, input, suggestion", Icons.Outlined.Label) { ChipsDemo(it) },
    Demo("cards", "Cards & Surfaces", "Elevated, filled, outlined", Icons.Outlined.CreditCard) { CardsDemo(it) },
    Demo("progress", "Progress", "Circular & linear indicators", Icons.Outlined.Refresh) { ProgressDemo(it) },
    Demo("dialogs", "Dialogs", "Alerts and custom dialogs", Icons.Outlined.Info) { DialogsDemo(it) },
    Demo("sheets", "Bottom Sheets", "Modal sheet with content", Icons.Outlined.ViewAgenda) { SheetsDemo(it) },
    Demo("snackbar", "Snackbars", "Messages with actions", Icons.Outlined.Notifications) { SnackbarDemo(it) },
    Demo("lists", "Lazy Lists & Grids", "Column, row, vertical grid", Icons.AutoMirrored.Outlined.List) { ListsDemo(it) },
    Demo("tabs", "Tabs", "Fixed and scrollable tabs", Icons.Outlined.Tab) { TabsDemo(it) },
    Demo("badges", "Badges", "Counts and dots on icons", Icons.Outlined.Label) { BadgesDemo(it) },
    Demo("appbars", "App Bars", "Top bars & navigation bar", Icons.Outlined.Dashboard) { AppBarsDemo(it) },
    Demo("layout", "Layout", "Row, Column, Box, weight", Icons.Outlined.VerticalSplit) { LayoutDemo(it) },
    Demo("animation", "Animation", "State, visibility, crossfade", Icons.Outlined.Animation) { AnimationDemo(it) }
)

/// Looks up a demo by its navigation [route].
internal fun demoFor(route: String?): Demo? = demos.firstOrNull { it.route == route }
