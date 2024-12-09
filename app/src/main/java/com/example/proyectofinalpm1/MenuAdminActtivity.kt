package com.example.proyectofinalpm1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuAdminActtivity: AppCompatActivity(), OnItemClickListener {

    private lateinit var btn_empleado: Button
    private lateinit var rv_empleado: RecyclerView
    private lateinit var empleadoAdapter: EmpleadoAdapter

    private lateinit var et_search: EditText
    private lateinit var ib_search: ImageButton
    private lateinit var ib_reset: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_empleados)

        btn_empleado = findViewById(R.id.btn_empleado)
        rv_empleado = findViewById(R.id.rv_empleados)
        rv_empleado.layoutManager = LinearLayoutManager(this)
        et_search = findViewById(R.id.et_buscar_empleado)
        ib_search = findViewById(R.id.ib_search_empleado)
        ib_reset = findViewById(R.id.ib_restart_empleado)

        btn_empleado.setOnClickListener{
            nuevoEmpleado()
        }

        ib_reset.setOnClickListener{
            reset()
        }

        ib_search.setOnClickListener{
            buscar()
        }

        empleadoAdapter = EmpleadoAdapter(MainActivity.empleados, this)
        rv_empleado.adapter = empleadoAdapter
    }

    private fun buscar() {
        val empleadosFiltrados = MainActivity.empleados.filter { it.nombre.equals(et_search.text.toString()) }
        empleadoAdapter = EmpleadoAdapter(empleadosFiltrados.toMutableList(), this)
        rv_empleado.adapter = empleadoAdapter
    }

    private fun reset() {
        empleadoAdapter = EmpleadoAdapter(MainActivity.empleados, this)
        rv_empleado.adapter = empleadoAdapter
    }

    private fun nuevoEmpleado() {
        val intent = Intent(this, AddEmpleadoActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        reset()

    }

    override fun onButtonClick(nota: Nota, buttonId: Int) {
        TODO("Not yet implemented")
    }

    override fun onButtonClick(empleado: Empleado, buttonId: Int) {
        deleteEmpleado(empleado)
    }

    private fun deleteEmpleado(empleado: Empleado) {
        MainActivity.empleados.remove(empleado)
        empleadoAdapter = EmpleadoAdapter(MainActivity.empleados, this)
        rv_empleado.adapter = empleadoAdapter
    }
}