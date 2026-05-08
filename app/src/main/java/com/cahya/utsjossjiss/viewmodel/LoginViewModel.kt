package com.cahya.utsjossjiss.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> get() = _loginStatus

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun attemptLogin(username: String, password: String) {
        if (username == "student" && password == "123") {
            _loginStatus.value = true
        } else {
            _loginStatus.value = false
            _errorMessage.value = "Username atau password salah!"
        }
    }
}
