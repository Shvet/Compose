package com.shvet.composelogin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shvet.composelogin.ui.theme.ComposeLoginTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardUI(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean,
    userName: String,
    userPassword: String,
    goToLogin: () -> Unit
) {
    if (!isLoggedIn) {
        goToLogin.invoke()
    }

    ComposeLoginTheme {
        Surface {
            Scaffold(modifier = modifier,
                topBar = {
                    TopAppBar(title = { Text(text = stringResource(id = R.string.dashboard)) })
                }
            ) {
                Column(modifier = modifier.padding(it)) {
                    if (isLoggedIn) {
                        Text(
                            text = "UserName:- $userName",
                            modifier = modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "Password:- $userPassword",
                            modifier = modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    } else {
                        ElevatedButton(
                            onClick = { goToLogin.invoke() },
                            modifier = modifier.wrapContentSize(),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(text = stringResource(id = R.string.login))
                        }
                    }

                }
            }
        }
    }
}