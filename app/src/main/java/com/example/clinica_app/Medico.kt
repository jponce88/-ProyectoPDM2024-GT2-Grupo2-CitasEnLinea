// Medico.kt
package com.example.clinica_app

import com.google.gson.annotations.SerializedName

data class Medico(
    val estado_medico: Int,
    val id_empresa: Int,
    val id_especialidad: Int,
    val id_medico: Int,
    val id_sucursal: Int,
    val id_tipo_doc_identidad: Int,
    val id_usuario: String,
    val numero_doc_identidad: String,
    val primer_apellido: String,
    val primer_nombre: String,
    val registro_jvpm: String,
    val segundo_apellido: String,
    val segundo_nombre: String
)

data class MedicoResponse(
    @SerializedName("Medico")
    val medicos: List<Medico>
)
