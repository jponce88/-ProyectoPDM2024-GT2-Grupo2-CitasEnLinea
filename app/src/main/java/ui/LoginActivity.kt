package ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.clinica_app.R
import network.LoginRequest
import network.LoginResponse
import viewmodel.LoginViewModel
import com.example.clinica_app.MainActivity
import android.util.Log


class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
            val idEmpresa = 1
            loginViewModel.login(username, password, idEmpresa)
            }
        }

        loginViewModel.loginResult.observe(this, Observer { result ->
            result.onSuccess { loginResponse ->

                val tag = "MiApp"
                val message = loginResponse.login.toString()
                Log.d(tag, message)

                if (loginResponse.login == 1) {
                    // Inicio de sesión exitoso
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    // Guardar el estado de autenticación y redirigir a la actividad principal
                    startActivity(Intent(this, MainActivity::class.java))
                    //finish()
                } else {
                    // Error al iniciar sesión
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }.onFailure { error ->
                Toast.makeText(this, "Login failed: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}