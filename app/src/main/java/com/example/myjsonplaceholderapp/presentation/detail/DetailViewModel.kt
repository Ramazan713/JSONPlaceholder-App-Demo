package com.example.myjsonplaceholderapp.presentation.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjsonplaceholderapp.domain.repo.PostRepo
import com.example.myjsonplaceholderapp.presentation.home.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    fun onEvent(event: DetailEvent){
        when(event){
            is DetailEvent.AddPost -> {
                viewModelScope.launch {
                    postRepo.addPost(event.body,args.userId)
                }
            }
            is DetailEvent.DeletePost -> {
                viewModelScope.launch {
                    postRepo.deletePostById(event.id)
                }
            }
            DetailEvent.Refresh -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    postRepo.refreshPosts(args.userId)
                }
            }
            is DetailEvent.UpdatePost -> {
                viewModelScope.launch {
                    val updatedPost = event.oldPost.copy(body = event.content)
                    postRepo.updatePost(updatedPost)
                }
            }
            is DetailEvent.ShowDialog -> {
                _state.update {
                    it.copy(dialogEvent = event.event)
                }
            }
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            postRepo.getFlowPostsByUserId(args.userId).collectLatest { posts->
                if(posts.isEmpty()){
                    onEvent(DetailEvent.Refresh)
                }
                _state.update {
                    it.copy(
                        isLoading = false,
                        items = posts
                    )
                }
            }

        }
    }
}