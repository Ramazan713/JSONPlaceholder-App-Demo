package com.example.myjsonplaceholderapp.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.myjsonplaceholderapp.presentation.detail.components.PostRow
import com.example.myjsonplaceholderapp.presentation.home.components.UserRow

@Composable
fun DetailPage(
    state: DetailState
) {
    Box(modifier = Modifier.fillMaxSize()){

        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(2f)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(state.items){item->
                PostRow(
                    post = item,
                    onDelete = {},
                    onUpdate = {}
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DetailPagePreview() {
    DetailPage(
        state = DetailState()
    )
}