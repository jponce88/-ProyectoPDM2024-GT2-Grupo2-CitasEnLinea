package com.example.clinica_app.ui.medico

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinica_app.HistorialResponse
import com.example.clinica_app.Medico
import com.example.clinica_app.MedicoAdapter
import com.example.clinica_app.MedicoResponse
import com.example.clinica_app.R
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var medicoAdapter: MedicoAdapter
    private var medicoList: MutableList<Medico> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_medico, container, false)
        recyclerView = root.findViewById(R.id.recyclerViewMedico)
        recyclerView.layoutManager = LinearLayoutManager(context)
        medicoAdapter = MedicoAdapter(medicoList)
        recyclerView.adapter = medicoAdapter
        fetchMedicos()
        return root
    }

    private fun fetchMedicos() {
        RetrofitClient.instance.getMedicos().enqueue(object : Callback<MedicoResponse> {
            override fun onResponse(call: Call<MedicoResponse>, response: Response<MedicoResponse>) {
                if (response.isSuccessful) {
                    val medicoResponse = response.body()
                    Log.d("MedicoFragment", "Response body: $medicoResponse")


                    if (medicoResponse?.medicos != null) {
                        medicoList.clear()
                        medicoList.addAll(medicoResponse.medicos)
                        medicoAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(context, "No Medico data found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("MedicoFragment", "Failed to fetch data: $errorBody")
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MedicoResponse>, t: Throwable) {
                Log.d("MedicoFragment", "An error occurred: ${t.message}")
                Toast.makeText(context, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
