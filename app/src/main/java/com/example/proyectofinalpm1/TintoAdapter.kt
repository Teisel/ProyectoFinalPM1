package com.example.proyectofinalpm1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TintoAdapter(private val notas: MutableList<Nota>,private val listener: OnItemClickListener) : RecyclerView.Adapter<TintoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TintoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tintoreria, parent, false)
        return TintoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TintoViewHolder, position: Int) {
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