package com.example.yelpcode.ui.screens.details.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yelpcode.R
import com.example.yelpcode.constants.GlobalConstants
import com.example.yelpcode.data.remote.model.Businesse
import com.example.yelpcode.domain.repository.BusinessRepository
import com.example.yelpcode.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val application: Application,
    private val repository: BusinessRepository
) : ViewModel() {

    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState.Loading)
    val state: StateFlow<UIState>
        get() = _state.asStateFlow()

    fun fetchDetailsById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            withTimeout(GlobalConstants.MAX_TIME_OUT) {
                if (state.value == UIState.Loading) {
                    val data = repository.fetchBusinessById(id = id)
                    _state.value = UIState.Success(
                        data = data
                    )
                }
            }
        } catch (tce: TimeoutCancellationException) {
            Log.e(this@DetailsViewModel.TAG(), "fetchDetailsById::tce::${tce.message}")
            _state.value = UIState.Error(
                message = tce.message ?: application.getString(R.string.time_out_error_TEXT)
            )
        } catch (e: Exception) {
            Log.e(this@DetailsViewModel.TAG(), "fetchDetailsById:::e::${e.message}")
            _state.value = UIState.Error(
                message = e.message ?: application.getString(R.string.generic_error_TEXT)
            )
        }
    }

    sealed class UIState {
        object Loading : UIState()
        class Success(val data: Businesse) : UIState()
        class Error(val message: String) : UIState()
    }
}