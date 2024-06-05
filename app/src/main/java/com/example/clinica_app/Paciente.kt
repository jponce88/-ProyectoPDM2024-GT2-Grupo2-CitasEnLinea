package com.example.clinica_app

data class Paciente(
    val id_afp: Int,
    val id_aseguradora: Int,
    val id_empresa: Int,
    val id_paciente: Int,
    val id_seguro_social: Int,
    val id_tipo_doc_identidad: Int,
    val id_usuario: String,
    val ingreso_mensual: String,
    val lugar_trabajo: String,
    val numero_afp: String,
    val numero_asegurado: String,
    val numero_doc_identidad: String,
    val numero_seguro_social: String,
    val primer_apellido: String,
    val primer_nombre: String,
    val segundo_apellido: String,
    val segundo_nombre: String
)

data class PacientesResponse(
    val pacientes: List<Paciente>
)