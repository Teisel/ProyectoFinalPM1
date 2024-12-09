package com.example.proyectofinalpm1.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalpm1.Empleado
import com.example.proyectofinalpm1.MainActivity
import com.example.proyectofinalpm1.MenuActivity
import com.example.proyectofinalpm1.Nota
import com.example.proyectofinalpm1.OnItemClickListener
import com.example.proyectofinalpm1.R
import com.example.proyectofinalpm1.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(), OnItemClickListener{

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var btn_mantenimiento: Button
    private lateinit var rv_mantenimiento: RecyclerView
    private lateinit var mantenimientoAdapter: MantenimientoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        btn_mantenimiento = root.findViewById(R.id.btn_nuevoMantenimiento)
        rv_mantenimiento = root.findViewById(R.id.rv_mantenimiento)
        rv_mantenimiento.layoutManager = LinearLayoutManager(requireContext())

        mantenimientoAdapter = MantenimientoAdapter(MainActivity.insumosLavanderia, this)
        rv_mantenimiento.adapter = mantenimientoAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onButtonClick(nota: Nota, buttonId: Int) {
        TODO("Not yet implemented")
    }

    override fun onButtonClick(nota: Empleado, buttonId: Int) {
        TODO("Not yet implemented")
    }
}