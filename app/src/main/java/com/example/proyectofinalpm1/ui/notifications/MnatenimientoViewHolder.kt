package com.example.proyectofinalpm1.ui.notifications

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalpm1.Insumo
import com.example.proyectofinalpm1.Nota
import com.example.proyectofinalpm1.OnItemClickListener
import com.example.proyectofinalpm1.R

class MnatenimientoViewHolder (view: View) : RecyclerView.ViewHolder(view){

    private val tv_nombre: TextView = itemView.findViewById(R.id.tv_nombre_mantenimiento)
    private val tv_cant: TextView = itemView.findViewById(R.id.tv_cantMantenimiento)
    //private val btn_less: Button = itemView.findViewById(R.id.btn_less_mantenimiento)
    //private val btn_add: Button = itemView.findViewById(R.id.btn_add_mantenimiento)

    fun bind(insumo: Insumo, listener: OnItemClickListener) {
        tv_nombre.text = insumo.nombre
        tv_cant.text = insumo.cantidad.toString()
        
        /*btn_less.setOnClickListener{
            var cant: Int = et_cant.text.toString().toInt()
            cant--
            if(cant < 0)
            {
                cant = 0
            }
            insumo.cantidad = cant
            et_cant.setText(cant.toString())
        }

        btn_add.setOnClickListener{
            var cant: Int = et_cant.text.toString().toInt()
            cant++
            insumo.cantidad = cant
            et_cant.setText(cant.toString())
        }*/

    }

}
