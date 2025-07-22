package com.jayr.pullrequest.ui.screens.organization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.pullrequest.domain.api.RetrofitClient.prApiInterface
import com.jayr.pullrequest.domain.models.Organization
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrganizationsViewmodel: ViewModel() {

    private val _organizations: MutableStateFlow<List<Organization>> = MutableStateFlow(emptyList())
    val organizations: StateFlow<List<Organization>> get() = _organizations

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        getContributions()
    }

    fun getContributions(){
      viewModelScope.launch {
        try {
            _organizations.value = prApiInterface.getOrganizations()
            _isLoading.value = _organizations.value.isEmpty()
        }catch (error: Error){
            _isLoading.value = _organizations.value.isEmpty()
        }
      }
    }



}