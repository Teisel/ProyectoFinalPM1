package com.example.proyectofinalpm1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EncargoAdapter(private val notas: MutableList<Nota>,
    private val listener: OnItemClickListener) : RecyclerView.Adapter<EncargosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EncargosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lavanderia, parent, false)
        return EncargosViewHolder(view)
    }

    override fun onBindViewHolder(holder: EncargosViewHolder, position: Int) {
        val nota = notas[position]
        holder.bind(nota, listener)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    fun addEncargo (encargo: Nota)
    {
        notas.add(encargo)
        notifyItemInserted(notas.size - 1)
    }
}