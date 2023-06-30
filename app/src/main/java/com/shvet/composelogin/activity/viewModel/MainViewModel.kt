package com.shvet.composelogin.activity.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shvet.composelogin.di.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(session: Session) : ViewModel() {

    val isLoggedIn = session.isUserLoggedIn()
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = false)
    val userName = session.getUserName()
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = "")
    val userPassword = session.getPassword()
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = "")

}