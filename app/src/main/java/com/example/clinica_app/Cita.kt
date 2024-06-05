package com.example.clinica_app

data class CitasResponse(
    val citas: List<Cita>
)

data class Cita(
    val fecha_hora: String,
    val id_cita: Int,
    val id_estado_cita: Int,
    val id_medico: Int,
    val id_modalidad: Int,
    val id_paciente: Int
)