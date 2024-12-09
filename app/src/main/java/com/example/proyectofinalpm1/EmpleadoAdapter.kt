package com.example.proyectofinalpm1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EmpleadoAdapter(private val empleados: MutableList<Empleado>,
                      private val listener: OnItemClickListener) : RecyclerView.Adapter<EmpleadoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpleadoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empleado, parent, false)
        return EmpleadoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmpleadoViewHolder, position: Int) {
        val empleado = empleados[position]
        holder.bind(empleado, listener)
    }

    override fun getItemCount(): Int {
        return empleados.size
    }

    fun addEncargo (empleado: Empleado)
    {
        empleados.add(empleado)
        notifyItemInserted(empleados.size - 1)
    }
}