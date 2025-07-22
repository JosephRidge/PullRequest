package com.jayr.pullrequest.ui.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.pullrequest.domain.api.RetrofitClient.prApiInterface
import com.jayr.pullrequest.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel: ViewModel(){
    private val _users: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val users: StateFlow<List<User>> get() = _users

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        getUsers()
    }

    fun getUsers(){
        viewModelScope.launch {
            try {
                _users.value = prApiInterface.getUsers()
                _isLoading.value = _users.value.isEmpty()
            }catch (error:Error)
            {
                _isLoading.value = _users.value.isEmpty()
            }
        }
    }
}