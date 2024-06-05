package network

data class LoginRequest(
    val id_usuario: String,
    val password_usuario: String,
    val id_empresa: Int
)