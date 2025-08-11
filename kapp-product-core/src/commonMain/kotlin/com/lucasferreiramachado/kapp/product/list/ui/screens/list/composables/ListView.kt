package com.lucasferreiramachado.kapp.product.list.ui.screens.list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListUiEvent
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ListScreenPreview(
    state: ListUiState = ListUiState(),
    events: List<ListUiEvent> = emptyList()
) {
    previewListScreen(state, events)
}

@Composable
fun ListView(
    state: ListUiState,
    onEvent: (ListUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .background(MaterialTheme.colorScheme.background),
    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
        ) {

            Text(
                text = buildAnnotatedString {
                    append("Products -> List")
                },
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = buildAnnotatedString {
                    append("Hello, \nwelcome to the product list page")
                },
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 50.dp, 0.dp, 0.dp)
            )

            LazyColumn {
                items(items = state.products) { item ->
                    ProductItem(item.name) {
                        onEvent(ListUiEvent.ProductItemSelected(item))
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.BottomCenter),
        ) {
            TextButton(
                onClick = {
                    onEvent(ListUiEvent.BackButtonPressed)
                },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp, 0.dp, 0.dp),
            ) {
                Text(
                    text = "Go to back",
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            Text(
                text = "KApp: Kotlin Multiplatform Multimodular App",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}


@Composable
fun ProductItem(name: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
    ) {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}