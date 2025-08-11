package com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.DeeplinkUiEvent
import com.lucasferreiramachado.kapp.product.compose.app.ui.screens.deeplink.DeeplinkUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun DeeplinkScreenPreview(
    state: DeeplinkUiState = DeeplinkUiState(
        deeplinkUri = "DEEPLINK URI"
    ),
    events: List<DeeplinkUiEvent> = emptyList()
) {
    previewDeeplinkScreen(state, events)
}

@Composable
fun DeeplinkView(
    state: DeeplinkUiState,
    onEvent: (DeeplinkUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .background(MaterialTheme.colorScheme.background),
    ) {
        if (state.showBackButton) {
            Button(
                onClick = { onEvent(DeeplinkUiEvent.OnBackPressed) }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(text = "Back",
                        modifier = Modifier.padding(start = 4.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                    )
                }
            }
        }
        Text(text = "An internal deeplink was called:\n\n${state.deeplinkUri}",
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
        )

        Text(text = "KAppProduct Example Compose App",
            modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.BottomCenter),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}