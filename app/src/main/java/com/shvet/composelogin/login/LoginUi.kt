package com.shvet.composelogin.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shvet.composelogin.R
import com.shvet.composelogin.login.viewModel.LoginViewModel
import com.shvet.composelogin.ui.theme.ComposeLoginTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUi(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    goToDashBoard: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    if (viewModel.isUserLoggedIn) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar(viewModel.userLoggedMessage)
            delay(100.milliseconds)
            goToDashBoard.invoke()
        }
    }
    ComposeLoginTheme {
        Surface {
            Scaffold(modifier = modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.login)) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                    )
                },
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) {
                Box(
                    modifier = modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = viewModel.userNameInput.input,
                            onValueChange = { text ->
                                viewModel.userNameInput = viewModel.userNameInput.copy(input = text)
                            },
                            modifier = modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedLabelColor = Color.Black,
                                unfocusedTextColor = Color.Gray,
                                focusedBorderColor = Color.Blue,
                                focusedTextColor = Color.Black
                            ),
                            label = { Text(text = stringResource(id = R.string.user_name))},
                            placeholder = { Text(text = stringResource(id = R.string.enter_user_name))}
                        )
                        AnimatedVisibility(visible = viewModel.userNameInput.isError) {
                            Text(
                                text = viewModel.userNameInput.errorText,
                                modifier = modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        OutlinedTextField(
                            value = viewModel.userPasswordInput.input,
                            onValueChange = { text ->
                                viewModel.userPasswordInput =
                                    viewModel.userPasswordInput.copy(input = text)
                            },
                            modifier = modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedLabelColor = Color.Black,
                                unfocusedTextColor = Color.Gray,
                                focusedBorderColor = Color.Blue,
                                focusedTextColor = Color.Black
                            ),
                            label = { Text(text = stringResource(id = R.string.user_password))},
                            placeholder = { Text(text = stringResource(id = R.string.enter_user_password))}
                        )
                        AnimatedVisibility(visible = viewModel.userPasswordInput.isError) {
                            Text(
                                text = viewModel.userPasswordInput.errorText,
                                modifier = modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                color = Color.Red,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        ElevatedButton(
                            onClick = {
                                viewModel.loginToServer()
                            },
                            modifier = modifier.wrapContentSize(),
                        ) {
                            Text(text = stringResource(id = R.string.login))
                        }
                    }
                }
            }
        }
    }
}