package com.shvet.composelogin.login.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shvet.composelogin.di.Session
import com.shvet.composelogin.login.model.LoginState
import com.shvet.composelogin.login.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val session: Session
) : ViewModel() {

    var userNameInput by mutableStateOf(LoginState())
    var userPasswordInput by mutableStateOf(LoginState())

    var isUserLoggedIn by mutableStateOf(false)
    var userLoggedMessage by mutableStateOf("")

    fun loginToServer() {
        if (userNameInput.input.isEmpty() || userNameInput.input.isBlank()) {
            userNameInput = userNameInput.copy(isError = true, errorText = "Please Enter UserName")
            return
        }
        if (userPasswordInput.input.isBlank() || userPasswordInput.input.isEmpty()) {
            userPasswordInput =
                userPasswordInput.copy(isError = true, errorText = "Please Enter Password")
            return
        }
        /*
        * Here we are calling REST API and fetching response, But for simplicity,
        * We will directly save user name and password to database
        * */
        viewModelScope.launch(Dispatchers.IO) {
            /*  try {
                  val response = repository.login(
                      userName = userNameInput.input,
                      userPassword = userPasswordInput.input
                  )
              } catch (e: Exception) {

              }*/
            session.setUserLoggedIn(true)
            session.setPassword(userPasswordInput.input)
            session.setUserName(userNameInput.input)
            isUserLoggedIn = true
            userLoggedMessage="${userNameInput.input} has successfully logged in!"
        }

    }
}