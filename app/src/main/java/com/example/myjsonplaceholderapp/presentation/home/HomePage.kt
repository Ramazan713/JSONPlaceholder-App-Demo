package com.example.myjsonplaceholderapp.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.myjsonplaceholderapp.presentation.home.components.UserRow

@Composable
fun HomePage(
    state: HomeState,
    onNavigateToDetail: (Int) -> Unit,
    onEvent: (HomeEvent) -> Unit,
) {


    Box(modifier = Modifier.fillMaxSize()){

        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(2f)
            )
        }

        LazyColumn {

            item {
                Button(onClick = {
                    onEvent(HomeEvent.Refresh)
                }) {
                    Text(text = "Refresh")
                }
            }

            items(state.items){item->
                UserRow(
                    user = item,
                    onClick = {
                        onNavigateToDetail(item.id)
                    },
                    onDelete = {
                        onEvent(HomeEvent.Delete(item))
                    }
                )
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(
        state = HomeState(),
        onNavigateToDetail = {},
        onEvent = {}
    )
}