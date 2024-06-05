package com.example.clinica_app.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinica_app.R
import com.example.clinica_app.PacienteAdapter
import com.example.clinica_app.Paciente
import com.example.clinica_app.PacientesResponse
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SlideshowFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pacienteAdapter: PacienteAdapter
    private var pacienteList: MutableList<Paciente> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        recyclerView = root.findViewById(R.id.recyclerViewPacientes)
        recyclerView.layoutManager = LinearLayoutManager(context)
        pacienteAdapter = PacienteAdapter(pacienteList)
        recyclerView.adapter = pacienteAdapter
        fetchPacientes()
        return root
    }

    private fun fetchPacientes() {
        RetrofitClient.instance.getPacientes().enqueue(object : Callback<PacientesResponse> {
            override fun onResponse(call: Call<PacientesResponse>, response: Response<PacientesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        pacienteList.clear()
                        pacienteList.addAll(it.pacientes)
                        pacienteAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PacientesResponse>, t: Throwable) {
                Toast.makeText(context, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
