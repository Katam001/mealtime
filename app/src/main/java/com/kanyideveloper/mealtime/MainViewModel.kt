
package com.kanyideveloper.mealtime

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kanyideveloper.core.domain.SubscriptionRepository
import com.kanyideveloper.core.domain.UserDataRepository
import com.kanyideveloper.core.state.SubscriptionStatusUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userDataRepository: UserDataRepository,
    private val subscriptionRepository: SubscriptionRepository
) : ViewModel() {
    val user = mutableStateOf(Firebase.auth.currentUser)

    val theme = userDataRepository.themeStream

    val isSubscribed: StateFlow<SubscriptionStatusUiState> =
        subscriptionRepository.isSubscribed
            .map(SubscriptionStatusUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SubscriptionStatusUiState.Loading
            )

    val subscriptionStatusUiState = subscriptionRepository.subscriptionStatusUiState
    fun updateSubscriptionStatus() {
        viewModelScope.launch {
            subscriptionRepository.updateSubscriptionStatus()
        }
    }

    init {
        updateSubscriptionStatus()
    }
}
