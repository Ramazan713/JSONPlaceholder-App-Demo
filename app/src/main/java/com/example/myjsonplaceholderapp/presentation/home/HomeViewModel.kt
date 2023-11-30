package com.example.myjsonplaceholderapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjsonplaceholderapp.domain.repo.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.Delete -> {
                viewModelScope.launch {
                    userRepo.deleteUser(event.user)
                }
            }
            HomeEvent.Refresh ->{
                viewModelScope.launch {
                    userRepo.refreshUsers()
                }
            }
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            userRepo.getFlowUsers().collectLatest { items->
                if(items.isEmpty()){
                    onEvent(HomeEvent.Refresh)
                }
                _state.update {
                    it.copy(
                        items = items,
                        isLoading = false
                    )
                }
            }

        }
    }
}