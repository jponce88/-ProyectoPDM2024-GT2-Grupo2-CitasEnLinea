package repository

import network.LoginRequest
import network.LoginResponse
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    fun login(username: String, password: String, id_empresa: Int, callback: (LoginResponse?, Throwable?) -> Unit) {
        val loginRequest = LoginRequest(username, password, id_empresa)
        val call = RetrofitClient.instance.login(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, Exception("Error en Inicio de Sesión"))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}