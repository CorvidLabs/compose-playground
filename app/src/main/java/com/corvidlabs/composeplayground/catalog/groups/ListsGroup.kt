package com.corvidlabs.composeplayground.catalog.groups

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.model.CodeExample
import com.corvidlabs.composeplayground.model.Component
import com.corvidlabs.composeplayground.model.ComponentGroup

/// Components in the [ComponentGroup.Lists] group: lazy lists, grids, and pagers.
internal val listsComponents: List<Component> get() = listOf(
    lazyColumnComponent,
    lazyRowComponent,
    lazyGridComponent,
    staggeredGridComponent,
    pagerComponent
)

private val lazyColumnComponent = Component(
    id = "lazy-column",
    name = "Lazy Column",
    group = ComponentGroup.Lists,
    summary = "Vertically scrolling list that composes items on demand",
    description = "LazyColumn lays out and composes only the items that are visible, making it " +
        "efficient for long or unbounded lists. Pair items with ListItem and HorizontalDivider " +
        "for a classic settings-style list.",
    docUrl = "https://developer.android.com/jetpack/compose/lists",
    related = listOf("lazy-row", "lazy-grid"),
    examples = listOf(
        CodeExample(
            title = "List of items",
            description = "A fixed-height LazyColumn of ListItems separated by dividers.",
            code = """
                LazyColumn(modifier = Modifier.height(220.dp)) {
                    items(rows) { row ->
                        ListItem(
                            headlineContent = { Text(row) },
                            supportingContent = { Text("Supporting text") }
                        )
                        HorizontalDivider()
                    }
                }
            """,
            demo = { LazyColumnDemo() }
        )
    )
)

private val lazyRowComponent = Component(
    id = "lazy-row",
    name = "Lazy Row",
    group = ComponentGroup.Lists,
    summary = "Horizontally scrolling list of fixed-size cards",
    description = "LazyRow is the horizontal counterpart to LazyColumn. It is well suited to " +
        "carousels of cards or thumbnails, with Arrangement.spacedBy adding consistent gaps " +
        "between items.",
    docUrl = "https://developer.android.com/jetpack/compose/lists",
    related = listOf("lazy-column", "lazy-grid"),
    examples = listOf(
        CodeExample(
            title = "Card carousel",
            description = "A LazyRow of fixed-size cards with spacing between them.",
            code = """
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(cards) { index ->
                        Card(modifier = Modifier.size(120.dp)) {
                            Box(contentAlignment = Alignment.Center) {
                                Text("Card #${'$'}index")
                            }
                        }
                    }
                }
            """,
            demo = { LazyRowDemo() }
        )
    )
)

private val lazyGridComponent = Component(
    id = "lazy-grid",
    name = "Lazy Grid",
    group = ComponentGroup.Lists,
    summary = "Two-dimensional grid with a fixed column count",
    description = "LazyVerticalGrid arranges items into columns described by GridCells. A " +
        "GridCells.Fixed value pins the column count, while aspectRatio keeps each cell square.",
    docUrl = "https://developer.android.com/jetpack/compose/lists#lazy-grids",
    related = listOf("lazy-column", "lazy-row", "staggered-grid"),
    examples = listOf(
        CodeExample(
            title = "Fixed three-column grid",
            description = "A fixed-height grid of square, colored cells.",
            code = """
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.height(220.dp)
                ) {
                    items(cells) { color ->
                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(color)
                        )
                    }
                }
            """,
            demo = { LazyGridDemo() }
        )
    )
)

private val staggeredGridComponent = Component(
    id = "staggered-grid",
    name = "Staggered Grid",
    group = ComponentGroup.Lists,
    summary = "Masonry-style grid where items have varying heights",
    description = "LazyVerticalStaggeredGrid packs items of different heights into columns, " +
        "creating a masonry layout. StaggeredGridCells.Fixed sets the column count.",
    docUrl = "https://developer.android.com/jetpack/compose/lists#lazy-staggered-grid",
    related = listOf("lazy-grid"),
    examples = listOf(
        CodeExample(
            title = "Masonry layout",
            description = "A fixed-height staggered grid of items with varying heights.",
            code = """
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing = 8.dp,
                    modifier = Modifier.height(220.dp)
                ) {
                    items(tiles) { tile ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(tile.height)
                                .clip(RoundedCornerShape(8.dp))
                                .background(tile.color)
                        )
                    }
                }
            """,
            demo = { StaggeredGridDemo() }
        )
    )
)

private val pagerComponent = Component(
    id = "pager",
    name = "Pager",
    group = ComponentGroup.Lists,
    summary = "Swipeable pages with a state-driven page count",
    description = "HorizontalPager lays out swipeable pages backed by a PagerState. The state " +
        "exposes currentPage, which can drive indicator dots beneath the pager.",
    docUrl = "https://developer.android.com/jetpack/compose/layouts/pager",
    related = listOf("tabs"),
    examples = listOf(
        CodeExample(
            title = "Horizontal pager with dots",
            description = "Four swipeable pages with indicator dots reflecting the current page.",
            code = """
                val state = rememberPagerState(pageCount = { 4 })
                HorizontalPager(state = state, modifier = Modifier.height(180.dp)) { page ->
                    Box(contentAlignment = Alignment.Center) {
                        Text("${'$'}{page + 1}", style = MaterialTheme.typography.displayLarge)
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(4) { index ->
                        val active = state.currentPage == index
                        Box(
                            modifier = Modifier
                                .size(if (active) 10.dp else 8.dp)
                                .clip(CircleShape)
                                .background(if (active) selected else unselected)
                        )
                    }
                }
            """,
            demo = { PagerDemo() }
        )
    )
)

private val listRows: List<String> = listOf(
    "Inbox",
    "Starred",
    "Snoozed",
    "Sent",
    "Drafts",
    "Spam",
    "Trash"
)

@Composable
private fun LazyColumnDemo() {
    LazyColumn(modifier = Modifier.height(220.dp)) {
        items(listRows) { row ->
            ListItem(
                headlineContent = { Text(row) },
                supportingContent = { Text("Supporting text") }
            )
            HorizontalDivider()
        }
    }
}

@Composable
private fun LazyRowDemo() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items((0 until 12).toList()) { index ->
            Card(modifier = Modifier.size(120.dp)) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Card #$index")
                }
            }
        }
    }
}

private val gridColors: List<Color> = listOf(
    Color(0xFFEF5350),
    Color(0xFFAB47BC),
    Color(0xFF5C6BC0),
    Color(0xFF29B6F6),
    Color(0xFF26A69A),
    Color(0xFF66BB6A),
    Color(0xFFFFCA28),
    Color(0xFFFF7043),
    Color(0xFF8D6E63)
)

@Composable
private fun LazyGridDemo() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(220.dp)
    ) {
        items(gridColors) { color ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color)
            )
        }
    }
}

private data class StaggeredTile(val height: androidx.compose.ui.unit.Dp, val color: Color)

private val staggeredTiles: List<StaggeredTile> = listOf(
    StaggeredTile(120.dp, Color(0xFFEF5350)),
    StaggeredTile(80.dp, Color(0xFF5C6BC0)),
    StaggeredTile(64.dp, Color(0xFF26A69A)),
    StaggeredTile(140.dp, Color(0xFFFFCA28)),
    StaggeredTile(96.dp, Color(0xFFAB47BC)),
    StaggeredTile(72.dp, Color(0xFF66BB6A)),
    StaggeredTile(110.dp, Color(0xFF29B6F6)),
    StaggeredTile(88.dp, Color(0xFFFF7043))
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun StaggeredGridDemo() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        modifier = Modifier.height(220.dp)
    ) {
        items(staggeredTiles) { tile ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(tile.height)
                    .clip(RoundedCornerShape(8.dp))
                    .background(tile.color)
            )
        }
    }
}

@Composable
private fun PagerDemo() {
    val state = rememberPagerState(pageCount = { 4 })
    val selected = MaterialTheme.colorScheme.primary
    val unselected = MaterialTheme.colorScheme.surfaceVariant
    HorizontalPager(state = state, modifier = Modifier.height(180.dp)) { page ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "${page + 1}",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        repeat(4) { index ->
            val active = state.currentPage == index
            Box(
                modifier = Modifier
                    .size(if (active) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (active) selected else unselected)
            )
        }
    }
}
