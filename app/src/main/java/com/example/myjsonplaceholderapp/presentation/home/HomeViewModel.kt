package com.example.myjsonplaceholderapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjsonplaceholderapp.domain.repo.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val userRepo: UserRepo
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    init {
        loadData()
    }

    fun refresh(){
        loadData(true)
    }

    private fun loadData(refresh: Boolean = false){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val items = userRepo.getUsers(refresh = refresh)
            _state.update {
                it.copy(
                    items = items,
                    isLoading = false
                )
            }
        }
    }
}