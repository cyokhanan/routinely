package com.cahya.utsjossjiss.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    fun checkLogin(username: String, password: String) {
        if (username == "student" && password == "123") {
            _loginResult.value = true
            _errorText.value = ""
        } else {
            _loginResult.value = false
            _errorText.value = "Username atau password salah"
        }
    }
}