package com.shvet.composelogin.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shvet.composelogin.DashboardUI
import com.shvet.composelogin.login.LoginUi
import com.shvet.composelogin.login.viewModel.LoginViewModel

@Composable
fun ComposeNavHost(
    modifier: Modifier,
    isLoggedIn: Boolean,
    userName: String,
    userPassword: String,
    navController: NavHostController = rememberNavController()
) {

    NavHost(modifier = modifier, navController = navController, startDestination = "Dashboard") {
        composable("Dashboard") {
            DashboardUI(
                modifier = modifier,
                isLoggedIn = isLoggedIn,
                userName = userName,
                userPassword = userPassword
            ) {
                navController.navigate("Login")
            }
        }
        composable("Login") {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginUi(modifier = modifier, viewModel = viewModel,
                goToDashBoard = {
                    navController.navigate("Dashboard") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}