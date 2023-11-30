package com.example.myjsonplaceholderapp.presentation.detail

import com.example.myjsonplaceholderapp.domain.models.Post

sealed interface DetailDialogEvent {

    data object AddPost: DetailDialogEvent

    data class UpdatePost(val post: Post): DetailDialogEvent
}