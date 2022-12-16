package com.example.yelpcode.ui.screens.businesses.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yelpcode.R
import com.example.yelpcode.constants.GlobalConstants
import com.example.yelpcode.domain.model.BusinessModel
import com.example.yelpcode.domain.repository.BusinessRepository
import com.example.yelpcode.domain.services.offline.MapperImpl
import com.example.yelpcode.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BusinessesViewModel @Inject constructor(
    private val businessRepository: BusinessRepository,
    private val application: Application
) : ViewModel() {

    private val _state = MutableStateFlow<UIState>(UIState.Loading)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    /**
     * Function that fetches businesses (pizza)
     * */
    fun fetchBusiness() = viewModelScope.launch {
        try {
            withTimeout(GlobalConstants.MAX_TIME_OUT) {
                withContext(Dispatchers.IO) {
                    if (_state.value == UIState.Loading) {

                        val data = businessRepository.fetchBusiness("pizza")
                        if (data.businesses.isNotEmpty()) {
                            _state.value = UIState.Success(
                                places = data.businesses.map {
                                    it?.let { place ->
                                        MapperImpl.toBusinessModel(place)
                                    }
                                }
                            )
                        } else {
                            _state.value = UIState.Error(
                                message = application.getString(R.string.there_is_no_data_available_TEXT)
                            )
                        }
                    }
                }
            }
        } catch (tce: TimeoutCancellationException) {
            Log.e(this@BusinessesViewModel.TAG(), "tce::fetchBusiness: ${tce.message}")
            _state.value = UIState.Error(
                message = tce.message ?: application.getString(R.string.time_out_error_TEXT)
            )
        } catch (e: Exception) {
            Log.e(this@BusinessesViewModel.TAG(), "e::fetchBusiness: ${e.message}")
            _state.value = UIState.Error(
                message = e.message ?: application.getString(R.string.generic_error_TEXT)
            )
        }
    }

    sealed class UIState {
        object Loading : UIState()
        class Success(val places: List<BusinessModel?>) : UIState()
        class Error(val message: String) : UIState()
    }

}