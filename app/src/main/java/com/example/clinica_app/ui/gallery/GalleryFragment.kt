package com.example.clinica_app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinica_app.R
import com.example.clinica_app.Cita
import com.example.clinica_app.CitasResponse
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var citaAdapter: CitaAdapter
    private var citaList: MutableList<Cita> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        citaAdapter = CitaAdapter(citaList)
        recyclerView.adapter = citaAdapter
        fetchCitas()
        return root
    }

    private fun fetchCitas() {
        RetrofitClient.instance.getCitas().enqueue(object : Callback<CitasResponse> {
            override fun onResponse(call: Call<CitasResponse>, response: Response<CitasResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        citaList.clear()
                        citaList.addAll(it.citas)
                        citaAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CitasResponse>, t: Throwable) {
                Toast.makeText(context, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
