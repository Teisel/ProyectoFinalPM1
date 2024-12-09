package com.example.proyectofinalpm1.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalpm1.Insumo
import com.example.proyectofinalpm1.Nota
import com.example.proyectofinalpm1.OnItemClickListener
import com.example.proyectofinalpm1.R
import com.example.proyectofinalpm1.TintoViewHolder

class MantenimientoAdapter (private val insumos: MutableList<Insumo>, private val listener: OnItemClickListener) : RecyclerView.Adapter<MnatenimientoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MnatenimientoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mantenimiento_encargado, parent, false)
        return MnatenimientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MnatenimientoViewHolder, position: Int) {
        val insumo = insumos[position]
        holder.bind(insumo, listener)
    }

    override fun getItemCount(): Int {
        return insumos.size
    }

    fun addEncargo (insumo: Insumo)
    {
        insumos.add(insumo)
        notifyItemInserted(insumos.size - 1)
    }

}
