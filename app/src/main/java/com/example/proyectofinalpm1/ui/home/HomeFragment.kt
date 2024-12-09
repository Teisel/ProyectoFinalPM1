package com.example.proyectofinalpm1.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalpm1.Empleado
import com.example.proyectofinalpm1.EncargoAdapter
import com.example.proyectofinalpm1.MainActivity
import com.example.proyectofinalpm1.MenuActivity
import com.example.proyectofinalpm1.Nota
import com.example.proyectofinalpm1.OnItemClickListener
import com.example.proyectofinalpm1.R
import com.example.proyectofinalpm1.TintoActivity
import com.example.proyectofinalpm1.TintoAdapter
import com.example.proyectofinalpm1.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var btn_tinto: Button
    private lateinit var rv_tinto: RecyclerView
    private lateinit var tintoAdapter: TintoAdapter

    private lateinit var et_search: EditText
    private lateinit var ib_search: ImageButton
    private lateinit var ib_reset: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        btn_tinto = root.findViewById(R.id.btn_tintoreria)
        rv_tinto = root.findViewById(R.id.rv_tinto)
        rv_tinto.layoutManager = LinearLayoutManager(requireContext())
        et_search = root.findViewById(R.id.et_buscar_tinto)
        ib_search = root.findViewById(R.id.ib_search_tinto)
        ib_reset = root.findViewById(R.id.ib_restart_tinto)

        btn_tinto.setOnClickListener{
            nuevoTinto()
        }

        ib_reset.setOnClickListener{
            reset()
        }

        ib_search.setOnClickListener{
            buscar()
        }

        tintoAdapter = TintoAdapter(MainActivity.notasTintoreria, this)
        rv_tinto.adapter = tintoAdapter

        return root
    }

    private fun buscar() {
        val encargosFiltrados = MainActivity.notasTintoreria.filter { it.nombreUsuaario.equals(et_search.text.toString()) }
        tintoAdapter = TintoAdapter(encargosFiltrados.toMutableList(), this)
        rv_tinto.adapter = tintoAdapter
    }

    private fun reset() {
        tintoAdapter = TintoAdapter(MainActivity.notasTintoreria, this)
        rv_tinto.adapter = tintoAdapter
    }

    private fun nuevoTinto() {
        val intent = Intent(requireContext(), TintoActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        tintoAdapter = TintoAdapter(MainActivity.notasTintoreria, this)
        rv_tinto.adapter = tintoAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onButtonClick(nota: Nota, buttonId: Int) {
        var state = ""

        if (buttonId == 1)
        {
            state = "Recibido"
        }
        if (buttonId == 2)
        {
            state = "En proceso"
        }
        if (buttonId == 3)
        {
            state = "Entregado"
            deleteNota(nota)
        }
        Toast.makeText(requireContext(), "Se cambio el estado a: " + state, Toast.LENGTH_SHORT).show()

    }

    override fun onButtonClick(nota: Empleado, buttonId: Int) {
        TODO("Not yet implemented")
    }

    private fun deleteNota(nota: Nota) {
        MainActivity.notasTintoreria.remove(nota)
        tintoAdapter = TintoAdapter(MainActivity.notasTintoreria, this)
        rv_tinto.adapter = tintoAdapter
    }
}