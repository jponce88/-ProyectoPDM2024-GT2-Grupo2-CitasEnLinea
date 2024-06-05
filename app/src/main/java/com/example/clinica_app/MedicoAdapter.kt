package com.example.clinica_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinica_app.Medico
import com.example.clinica_app.R

class MedicoAdapter(private val medicoList: List<Medico>) :
    RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder>() {

    class MedicoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val primernombreTextView: TextView = view.findViewById(R.id.primer_nombreTextView)
        val segundonombreTextView: TextView = view.findViewById(R.id.segundo_nombreTextView)
        val primerapellidoTextView: TextView = view.findViewById(R.id.primer_apellidoTextView)
        val segundoapellidoTextView: TextView = view.findViewById(R.id.segundo_apellidoTextView)
        val registroJVPMTextView: TextView = view.findViewById(R.id.registro_jvpmTextView)
        val docIdentidadTextView: TextView = view.findViewById(R.id.numero_doc_identidadTextView)
        val usuarioTextView: TextView = view.findViewById(R.id.id_usuarioTextView)
        val estadoMedicoTextView: TextView = view.findViewById(R.id.estado_medicoTextView)
        val idEmpresaTextView: TextView = view.findViewById(R.id.id_empresaTextView)
        val idEspecialidadTextView: TextView = view.findViewById(R.id.id_especialidadTextView)
        val idMedicoTextView: TextView = view.findViewById(R.id.id_medico)
        val idSucursalTextView: TextView = view.findViewById(R.id.id_sucursalTextView)
        val idTipoDocIdentidadTextView: TextView = view.findViewById(R.id.id_tipo_doc_identidadTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoAdapter.MedicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medico, parent, false)
        return MedicoAdapter.MedicoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicoAdapter.MedicoViewHolder, position: Int) {
        val medico = medicoList[position]
        holder.primernombreTextView.text = "Registro JVPM: ${medico.primer_nombre}"
        holder.segundonombreTextView.text = "Registro JVPM: ${medico.segundo_nombre}"
        holder.primerapellidoTextView.text = "Registro JVPM: ${medico.primer_apellido}"
        holder.segundoapellidoTextView.text = "Registro JVPM: ${medico.segundo_apellido}"
        holder.registroJVPMTextView.text = "Registro JVPM: ${medico.registro_jvpm}"
        holder.docIdentidadTextView.text = "Documento de Identidad: ${medico.numero_doc_identidad}"
        holder.usuarioTextView.text = "Usuario: ${medico.id_usuario}"
        holder.estadoMedicoTextView.text = "Estado Médico: ${medico.estado_medico}"
        holder.idEmpresaTextView.text = "ID Empresa: ${medico.id_empresa}"
        holder.idEspecialidadTextView.text = "ID Especialidad: ${medico.id_especialidad}"
        holder.idMedicoTextView.text = "ID Médico: ${medico.id_medico}"
        holder.idSucursalTextView.text = "ID Sucursal: ${medico.id_sucursal}"
        holder.idTipoDocIdentidadTextView.text = "ID Tipo Doc Identidad: ${medico.id_tipo_doc_identidad}"
    }

    override fun getItemCount(): Int {
        return medicoList.size
    }
}
