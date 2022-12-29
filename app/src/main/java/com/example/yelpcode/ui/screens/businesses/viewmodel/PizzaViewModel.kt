package com.example.yelpcode.ui.screens.businesses.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yelpcode.domain.model.BusinessModel
import com.example.yelpcode.domain.repository.BusinessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor(
    private val repository: BusinessRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UIState(loading = true))
    val state = _state.asStateFlow()

    fun fetchPizzas() = viewModelScope.launch(Dispatchers.IO) {
        val pizzas = repository.fetchBusiness("pizzas")
        _state.value = UIState(true)
        _state.value = UIState(data = pizzas)
    }

    data class UIState(
        val loading: Boolean = false,
        val data: List<BusinessModel?> = emptyList()
    )
}