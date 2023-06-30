package com.shvet.composelogin.login.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginState(
    val input: String = "",
    val isError: Boolean = false,
    val errorText: String = ""
) : Parcelable
