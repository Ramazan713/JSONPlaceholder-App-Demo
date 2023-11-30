package com.example.myjsonplaceholderapp.presentation.home

import com.example.myjsonplaceholderapp.domain.models.User

sealed interface HomeEvent {

    data object Refresh: HomeEvent

    data class Delete(val user: User): HomeEvent
}