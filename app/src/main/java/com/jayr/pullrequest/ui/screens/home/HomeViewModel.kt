package com.jayr.pullrequest.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.pullrequest.domain.api.RetrofitClient.prApiInterface
import com.jayr.pullrequest.domain.models.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> get() = _projects

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        viewModelScope.launch{
            getProjects()
        }
    }

    suspend fun getProjects() {
        try {
            _projects.value = prApiInterface.getProjects()
            _isLoading.value = _projects.value.isEmpty()
        }catch (error: Error){
            _isLoading.value = _projects.value.isEmpty()
        }
    }
}