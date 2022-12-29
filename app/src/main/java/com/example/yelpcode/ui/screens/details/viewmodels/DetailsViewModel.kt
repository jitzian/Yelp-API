package com.example.yelpcode.ui.screens.details.viewmodels

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
class DetailsViewModel @Inject constructor(
    private val repository: BusinessRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UIState(loading = true))
    val state = _state.asStateFlow()

    /**
     * [id]: Identifier for specific Business / Place
     * */
    fun fetchDetailsById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        val place = repository.fetchBusinessById(id)
        _state.value = UIState(true)
        _state.value = UIState(data = place)
    }

    data class UIState(
        val loading: Boolean = false,
        val data: BusinessModel = BusinessModel()
    )
}