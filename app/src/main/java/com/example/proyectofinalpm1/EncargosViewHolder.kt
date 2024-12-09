package com.example.proyectofinalpm1

import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EncargosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val tv_idNota: TextView = itemView.findViewById(R.id.tv_idNota)
    private val tv_usuario: TextView = itemView.findViewById(R.id.tv_usuario)
    private val tv_fechaRecibido: TextView = itemView.findViewById(R.id.tv_fechaRecibido)
    private val tv_fechaEntrega: TextView = itemView.findViewById(R.id.tv_fechaEntrega)
    private val ib_wash: ImageButton = itemView.findViewById(R.id.ib_wash)
    private val ib_heat: ImageButton = itemView.findViewById(R.id.ib_heat)
    private val ib_entrega: ImageButton = itemView.findViewById(R.id.ib_entrega)


    fun bind(nota: Nota, listener: OnItemClickListener) {
        tv_idNota.text = nota.idNota.toString()
        tv_usuario.text = nota.nombreUsuaario
        tv_fechaRecibido.text = nota.fechaPago
        tv_fechaEntrega.text = nota.fechaEntrega

        ib_wash.setOnClickListener{
            listener.onButtonClick(nota, 1)
        }

        ib_heat.setOnClickListener{
            listener.onButtonClick(nota,2)
        }

        ib_entrega.setOnClickListener{
            listener.onButtonClick(nota, 3)
        }
    }


}
