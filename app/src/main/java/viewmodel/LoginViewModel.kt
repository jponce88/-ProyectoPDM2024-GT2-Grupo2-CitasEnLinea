package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import network.LoginResponse
import repository.UserRepository

class LoginViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    fun login(username: String, password: String, id_empresa: Int) {
        userRepository.login(username, password, id_empresa) { response, error ->
            if (response != null) {
                _loginResult.postValue(Result.success(response))
            } else {
                _loginResult.postValue(Result.failure(error ?: Exception("Unknown error")))
            }
        }
    }
}