package com.example.proyectofinalpm1

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class AddEmpleadoActivity: AppCompatActivity() {

    private lateinit var et_Nombre: EditText
    private lateinit var et_telefono: EditText
    private lateinit var et_correo: EditText
    private lateinit var et_pass: EditText
    private lateinit var et_salario: EditText
    private lateinit var et_iTurno: EditText
    private lateinit var et_fTurno: EditText
    private lateinit var et_fCont: EditText
    private lateinit var s_tipo: Spinner
    private lateinit var btn_add: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empleado)

        et_Nombre = findViewById(R.id.editTextNombre)
        et_telefono = findViewById(R.id.editTextTelefono)
        et_correo = findViewById(R.id.editTextCorreo)
        et_pass = findViewById(R.id.editTextPass)
        et_salario = findViewById(R.id.editTextSalario)
        et_iTurno = findViewById(R.id.editTextInicioTurno)
        et_fTurno = findViewById(R.id.editTextFinTurno)
        et_fCont = findViewById(R.id.editTextFechaContratacion)
        s_tipo = findViewById(R.id.spinnerTipo)
        btn_add = findViewById(R.id.buttonAgregarEmpleado)

        val items = arrayOf("A", "E")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        s_tipo.adapter = adapter

        btn_add.setOnClickListener{
            addEmpleado()
        }

    }

    private fun addEmpleado() {
        if((et_Nombre.text.isEmpty()) ||
            (et_telefono.text.isEmpty()) ||
            (et_correo.text.isEmpty()) ||
            (et_pass.text.isEmpty()) ||
            (et_salario.text.isEmpty()) ||
            (et_iTurno.text.isEmpty()) ||
            (et_fTurno.text.isEmpty()) ||
            (et_fCont.text.isEmpty())
        )
        {
            Toast.makeText(this, "Favor de completer todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        var empleado = Empleado(Random.nextInt(1,9999),
            et_salario.text.toString().toDouble(),
            et_iTurno.text.toString(), et_fTurno.text.toString(),
            et_fCont.text.toString(), s_tipo.selectedItem.toString())

        empleado.nombre = et_Nombre.text.toString()
        empleado.telefono = et_telefono.text.toString()
        empleado.correo = et_correo.text.toString()
        empleado.pass = et_pass.text.toString()

         MainActivity.empleados.add(empleado)
        setResult(Activity.RESULT_OK)
        finish()
    }
}