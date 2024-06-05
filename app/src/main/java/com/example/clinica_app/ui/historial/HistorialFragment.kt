package com.example.clinica_app.ui.historial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinica_app.R
import com.example.clinica_app.Historial
import com.example.clinica_app.HistorialResponse
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistorialFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var historialAdapter: HistorialAdapter
    private var historialList: MutableList<Historial> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_historial, container, false)
        recyclerView = root.findViewById(R.id.recyclerViewHistorial)
        recyclerView.layoutManager = LinearLayoutManager(context)
        historialAdapter = HistorialAdapter(historialList)
        recyclerView.adapter = historialAdapter
        fetchHistorial()
        return root
    }

    private fun fetchHistorial() {
        RetrofitClient.instance.getHistorial().enqueue(object : Callback<HistorialResponse> {
            override fun onResponse(call: Call<HistorialResponse>, response: Response<HistorialResponse>) {
                if (response.isSuccessful) {
                    val historialResponse = response.body()
                    Log.d("HistorialFragment", "Response body: $historialResponse")


                    if (historialResponse?.historiales != null) {
                        historialList.clear()
                        historialList.addAll(historialResponse.historiales)
                        historialAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(context, "No historial data found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("HistorialFragment", "Failed to fetch data: $errorBody")
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<HistorialResponse>, t: Throwable) {
                Log.d("HistorialFragment", "An error occurred: ${t.message}")
                Toast.makeText(context, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
