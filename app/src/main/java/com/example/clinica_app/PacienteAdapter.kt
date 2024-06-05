package com.example.clinica_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinica_app.R
import com.example.clinica_app.Paciente

class PacienteAdapter(private val pacienteList: List<Paciente>) :
    RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>() {

    class PacienteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val apellidoTextView: TextView = view.findViewById(R.id.apellidoTextView)
        val idAfpTextView: TextView = view.findViewById(R.id.idAfpTextView)
        val idAseguradoraTextView: TextView = view.findViewById(R.id.idAseguradoraTextView)
        val idEmpresaTextView: TextView = view.findViewById(R.id.idEmpresaTextView)
        val idPacienteTextView: TextView = view.findViewById(R.id.idPacienteTextView)
        val idSeguroSocialTextView: TextView = view.findViewById(R.id.idSeguroSocialTextView)
        val idTipoDocIdentidadTextView: TextView = view.findViewById(R.id.idTipoDocIdentidadTextView)
        val idUsuarioTextView: TextView = view.findViewById(R.id.idUsuarioTextView)
        val ingresoMensualTextView: TextView = view.findViewById(R.id.ingresoMensualTextView)
        val lugarTrabajoTextView: TextView = view.findViewById(R.id.lugarTrabajoTextView)
        val numeroAfpTextView: TextView = view.findViewById(R.id.numeroAfpTextView)
        val numeroAseguradoTextView: TextView = view.findViewById(R.id.numeroAseguradoTextView)
        val numeroDocIdentidadTextView: TextView = view.findViewById(R.id.numeroDocIdentidadTextView)
        val numeroSeguroSocialTextView: TextView = view.findViewById(R.id.numeroSeguroSocialTextView)
        val primerApellidoTextView: TextView = view.findViewById(R.id.primerApellidoTextView)
        val primerNombreTextView: TextView = view.findViewById(R.id.primerNombreTextView)
        val segundoApellidoTextView: TextView = view.findViewById(R.id.segundoApellidoTextView)
        val segundoNombreTextView: TextView = view.findViewById(R.id.segundoNombreTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_paciente, parent, false)
        return PacienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = pacienteList[position]
        holder.nombreTextView.text = "${paciente.primer_nombre} ${paciente.segundo_nombre}"
        holder.apellidoTextView.text = "${paciente.primer_apellido} ${paciente.segundo_apellido}"
        holder.idAfpTextView.text = "ID AFP: ${paciente.id_afp}"
        holder.idAseguradoraTextView.text = "ID Aseguradora: ${paciente.id_aseguradora}"
        holder.idEmpresaTextView.text = "ID Empresa: ${paciente.id_empresa}"
        holder.idPacienteTextView.text = "ID Paciente: ${paciente.id_paciente}"
        holder.idSeguroSocialTextView.text = "ID Seguro Social: ${paciente.id_seguro_social}"
        holder.idTipoDocIdentidadTextView.text = "ID Tipo Doc. Identidad: ${paciente.id_tipo_doc_identidad}"
        holder.idUsuarioTextView.text = "ID Usuario: ${paciente.id_usuario}"
        holder.ingresoMensualTextView.text = "Ingreso Mensual: ${paciente.ingreso_mensual}"
        holder.lugarTrabajoTextView.text = "Lugar de Trabajo: ${paciente.lugar_trabajo}"
        holder.numeroAfpTextView.text = "Número AFP: ${paciente.numero_afp}"
        holder.numeroAseguradoTextView.text = "Número Asegurado: ${paciente.numero_asegurado}"
        holder.numeroDocIdentidadTextView.text = "Número Doc. Identidad: ${paciente.numero_doc_identidad}"
        holder.numeroSeguroSocialTextView.text = "Número Seguro Social: ${paciente.numero_seguro_social}"
        holder.primerApellidoTextView.text = "Primer Apellido: ${paciente.primer_apellido}"
        holder.primerNombreTextView.text = "Primer Nombre: ${paciente.primer_nombre}"
        holder.segundoApellidoTextView.text = "Segundo Apellido: ${paciente.segundo_apellido}"
        holder.segundoNombreTextView.text = "Segundo Nombre: ${paciente.segundo_nombre}"
    }

    override fun getItemCount(): Int {
        return pacienteList.size
    }
}