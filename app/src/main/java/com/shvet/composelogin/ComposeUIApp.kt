package com.shvet.composelogin

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.shvet.composelogin.activity.ComposeNavHost
import com.shvet.composelogin.activity.viewModel.MainViewModel
import com.shvet.composelogin.ui.theme.ComposeLoginTheme

@Composable
fun ComposeUIApp() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    val isLoggedIn by mainViewModel.isLoggedIn.collectAsStateWithLifecycle()
    val userName by mainViewModel.userName.collectAsStateWithLifecycle()
    val userPassword by mainViewModel.userPassword.collectAsStateWithLifecycle()
    Log.e("IsLoggedIn", " $isLoggedIn")
    ComposeLoginTheme {
        ComposeNavHost(
            modifier = Modifier,
            isLoggedIn = isLoggedIn,
            userName = userName,
            userPassword = userPassword,
            navController = navController
        )
    }
}