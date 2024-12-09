package com.example.proyectofinalpm1

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmpleadoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    private val tv_idEmpleado: TextView = itemView.findViewById(R.id.tv_idEmpleado)
    private val tv_usuario: TextView = itemView.findViewById(R.id.tv_usuario_empleado)
    private val tv_horaEntrada: TextView = itemView.findViewById(R.id.tv_horaEntrada)
    private val tv_horaSalida: TextView = itemView.findViewById(R.id.tv_horaSalida)
    private val tv_tipoUsuario: TextView = itemView.findViewById(R.id.tv_tipoUsuario)
    private val ib_eliminar_usuario: ImageButton = itemView.findViewById(R.id.ib_eliminar_usuario)


    fun bind(empleado: Empleado, listener: OnItemClickListener) {
        tv_idEmpleado.text = empleado.getidEmpleado().toString()
        tv_usuario.text = empleado.nombre
        tv_horaEntrada.text = empleado.getHE()
        tv_horaSalida.text = empleado.getHS()
        tv_tipoUsuario.text = empleado.getTipo()

        ib_eliminar_usuario.setOnClickListener{
            if(!empleado.getTipo().equals("A"))
            {
                listener.onButtonClick(empleado, 1)
            }
        }
    }


}
