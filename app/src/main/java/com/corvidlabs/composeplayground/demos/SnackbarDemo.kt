package com.corvidlabs.composeplayground.demos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.corvidlabs.composeplayground.ui.DemoBlock
import kotlinx.coroutines.launch

@Composable
internal fun SnackbarDemo(padding: PaddingValues) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf("Trigger a snackbar below.") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DemoBlock("Simple message") {
                Button(onClick = {
                    scope.launch { snackbarHostState.showSnackbar("Saved to your library") }
                }) { Text("Show snackbar") }
            }

            DemoBlock("With action & result") {
                Button(onClick = {
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Item archived",
                            actionLabel = "Undo",
                            duration = SnackbarDuration.Short
                        )
                        status = when (result) {
                            SnackbarResult.ActionPerformed -> "Undo pressed"
                            SnackbarResult.Dismissed -> "Snackbar dismissed"
                        }
                    }
                }) { Text("Archive with undo") }
                Text(status, style = MaterialTheme.typography.bodyMedium)
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(padding)
        )
    }
}
