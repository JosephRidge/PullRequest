package com.jayr.pullrequest.ui.screens.organization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayr.pullrequest.domain.api.RetrofitClient.prApiInterface
import com.jayr.pullrequest.domain.models.Organization
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrganizationsViewmodel: ViewModel() {

//    states
    private val _organizations: MutableStateFlow<List<Organization>> = MutableStateFlow(emptyList())
    val organizations: StateFlow<List<Organization>> get() = _organizations

//    init
    init {
        getContributions()
    }

//    functions
    fun getContributions(){
      viewModelScope.launch {
          _organizations.value = prApiInterface.getOrganizations()
      }
    }

}