package com.example.myjsonplaceholderapp.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjsonplaceholderapp.domain.repo.PostRepo
import com.example.myjsonplaceholderapp.presentation.home.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetailArgs(val userId: Int){
    constructor(savedStateHandle: SavedStateHandle): this(
        checkNotNull(savedStateHandle["userId"]) as Int
    )
}


class DetailViewModel constructor(
    private val postRepo: PostRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    val args = DetailArgs(savedStateHandle)

    init {
        loadData()
    }

    fun refresh(){
        loadData(true)
    }

    private fun loadData(refresh: Boolean = false){

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val posts = postRepo.getPostsByUserId(args.userId,refresh)
            _state.update {
                it.copy(
                    isLoading = false,
                    items = posts
                )
            }
        }
    }
}