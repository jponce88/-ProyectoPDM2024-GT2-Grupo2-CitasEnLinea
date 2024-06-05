package com.example.clinica_app.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinica_app.R
import com.example.clinica_app.Cita

class CitaAdapter(private val citaList: List<Cita>) :
    RecyclerView.Adapter<CitaAdapter.CitaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cita, parent, false)
        return CitaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        val cita = citaList[position]
        holder.fechaHoraTextView.text = "Fecha y Hora: ${cita.fecha_hora}"
        holder.idCitaTextView.text = "ID Cita: ${cita.id_cita}"
        holder.idEstadoCitaTextView.text = "ID Estado Cita: ${cita.id_estado_cita}"
        holder.idMedicoTextView.text = "ID Médico: ${cita.id_medico}"
        holder.idModalidadTextView.text = "ID Modalidad: ${cita.id_modalidad}"
        holder.idPacienteTextView.text = "ID Paciente: ${cita.id_paciente}"
    }

    override fun getItemCount() = citaList.size

    class CitaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fechaHoraTextView: TextView = itemView.findViewById(R.id.fecha_hora)
        val idCitaTextView: TextView = itemView.findViewById(R.id.id_cita)
        val idEstadoCitaTextView: TextView = itemView.findViewById(R.id.id_estado_cita)
        val idMedicoTextView: TextView = itemView.findViewById(R.id.id_medico)
        val idModalidadTextView: TextView = itemView.findViewById(R.id.id_modalidad)
        val idPacienteTextView: TextView = itemView.findViewById(R.id.id_paciente)
    }
}
