package com.jayr.pullrequest.ui.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.pullrequest.domain.api.RetrofitClient.prApiInterface
import com.jayr.pullrequest.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel: ViewModel(){
//    state
    private val _users: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val users: StateFlow<List<User>> get() = _users

//    init
    init {
        getUsers()
    }

//    functions
    fun getUsers(){
        viewModelScope.launch {
            _users.value = prApiInterface.getUsers()
        }
    }
}