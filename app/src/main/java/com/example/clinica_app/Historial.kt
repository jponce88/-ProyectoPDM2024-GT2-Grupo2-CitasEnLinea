package com.example.clinica_app

import com.google.gson.annotations.SerializedName

data class Historial(
    val descripcion: String,
    val diagnostico: String,
    val fecha_registro: String,
    val id_historial: Int,
    val id_medico: Int,
    val id_paciente: Int,
    val medicamentos: String,
    val observaciones: String,
    val procedimientos_realizados: String,
    val resultados_pruebas: String,
    val tratamiento: String
)

data class HistorialResponse(
    @SerializedName("Historial")
    val historiales: List<Historial>
)
