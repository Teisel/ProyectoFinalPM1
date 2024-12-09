package com.example.proyectofinalpm1

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TintoViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val tv_idNotaTinto: TextView = itemView.findViewById(R.id.tv_idNota_tinto)
    private val tv_usuario: TextView = itemView.findViewById(R.id.tv_usuario_tinto)
    private val tv_fechaRecibido: TextView = itemView.findViewById(R.id.tv_fechaRecibido_tinto)
    private val tv_fechaEntrega: TextView = itemView.findViewById(R.id.tv_fechaEntrega_tinto)
    private val ib_recibido: ImageButton = itemView.findViewById(R.id.ib_recibido)
    private val ib_proceso: ImageButton = itemView.findViewById(R.id.ib_proceso)
    private val ib_listo: ImageButton = itemView.findViewById(R.id.ib_listo)


    fun bind(nota: Nota, listener: OnItemClickListener) {
        tv_idNotaTinto.text = nota.idNota.toString()
        tv_usuario.text = nota.nombreUsuaario
        tv_fechaRecibido.text = nota.fechaPago
        tv_fechaEntrega.text = nota.fechaEntrega
        ib_proceso.isEnabled = !ib_proceso.isEnabled
        ib_listo.isEnabled = !ib_listo.isEnabled

        ib_recibido.setOnClickListener{
            ib_recibido.isEnabled = !ib_recibido.isEnabled
            ib_proceso.isEnabled = !ib_proceso.isEnabled
            listener.onButtonClick(nota,1)
        }

        ib_proceso.setOnClickListener{
            ib_proceso.isEnabled = !ib_proceso.isEnabled
            ib_listo.isEnabled = !ib_listo.isEnabled
            listener.onButtonClick(nota,2)
        }

        ib_listo.setOnClickListener{
            listener.onButtonClick(nota, 3)
        }
    }

}
