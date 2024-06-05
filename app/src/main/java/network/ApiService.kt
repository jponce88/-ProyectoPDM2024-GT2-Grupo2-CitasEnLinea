package network

import com.example.clinica_app.CitasResponse
import com.example.clinica_app.PacientesResponse
import com.example.clinica_app.HistorialResponse
import com.example.clinica_app.MedicoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login/")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("citas/")
    fun getCitas(): Call<CitasResponse>

    @GET("pacientes/")
    fun getPacientes(): Call<PacientesResponse>

    @GET("historiales_medicos/")
    fun getHistorial(): Call<HistorialResponse>

    @GET("medicos/")
    fun getMedicos(): Call<MedicoResponse>
}

