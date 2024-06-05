package com.example.clinica_app.ui.historial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinica_app.R
import com.example.clinica_app.Historial

class HistorialAdapter(private val historialList: List<Historial>) :
    RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {

    class HistorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descripcionTextView: TextView = view.findViewById(R.id.descripcionTextView)
        val diagnosticoTextView: TextView = view.findViewById(R.id.diagnosticoTextView)
        val fechaRegistroTextView: TextView = view.findViewById(R.id.fecha_registroTextView)
        val idHistorialTextView: TextView = view.findViewById(R.id.id_historialTextView)
        val idMedicoTextView: TextView = view.findViewById(R.id.id_medicoTextView)
        val idPacienteTextView: TextView = view.findViewById(R.id.id_pacienteTextView)
        val medicamentosTextView: TextView = view.findViewById(R.id.medicamentosTextView)
        val observacionesTextView: TextView = view.findViewById(R.id.observacionesTextView)
        val procedimientosRealizadosTextView: TextView = view.findViewById(R.id.procedimientos_realizadosTextView)
        val resultadosPruebasTextView: TextView = view.findViewById(R.id.resultados_pruebasTextView)
        val tratamientoTextView: TextView = view.findViewById(R.id.tratamientoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historial, parent, false)
        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val historial = historialList[position]
        holder.descripcionTextView.text = "Descripción: ${historial.descripcion}"
        holder.diagnosticoTextView.text = "Diagnóstico: ${historial.diagnostico}"
        holder.fechaRegistroTextView.text = "Fecha de Registro: ${historial.fecha_registro}"
        holder.idHistorialTextView.text = "ID Historial: ${historial.id_historial}"
        holder.idMedicoTextView.text = "ID Médico: ${historial.id_medico}"
        holder.idPacienteTextView.text = "ID Paciente: ${historial.id_paciente}"
        holder.medicamentosTextView.text = "Medicamentos: ${historial.medicamentos}"
        holder.observacionesTextView.text = "Observaciones: ${historial.observaciones}"
        holder.procedimientosRealizadosTextView.text = "Procedimientos Realizados: ${historial.procedimientos_realizados}"
        holder.resultadosPruebasTextView.text = "Resultados de Pruebas: ${historial.resultados_pruebas}"
        holder.tratamientoTextView.text = "Tratamiento: ${historial.tratamiento}"
    }

    override fun getItemCount(): Int {
        return historialList.size
    }
}
