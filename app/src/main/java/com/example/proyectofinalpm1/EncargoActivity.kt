package com.example.proyectofinalpm1

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.random.Random

class EncargoActivity : AppCompatActivity() {

    private lateinit var et_nombre: EditText
    private lateinit var et_cant: EditText
    private lateinit var btn_plus: Button
    private lateinit var btn_less: Button
    private lateinit var btn_aceptar: Button
    private lateinit var btn_cancelar: Button
    private lateinit var calendar: CalendarView
    private lateinit var tv_total: TextView
    private var selectedDate: String = ""

    private var encargo: ProductoNota = ProductoNota(0)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_encargo)

        et_nombre = findViewById(R.id.et_nombreEncargo)
        et_cant = findViewById(R.id.et_cantEncargo)
        btn_plus = findViewById(R.id.btn_plus)
        btn_less = findViewById(R.id.btn_less)
        btn_aceptar = findViewById(R.id.btn_aceptar)
        btn_cancelar = findViewById(R.id.btn_cancelar)
        calendar = findViewById(R.id.calendarView)
        tv_total = findViewById(R.id.tv_total)

        encargo.nombre = "Encargo"
        encargo.setCantidad(0)
        encargo.tipo = "E"
        encargo.precio = 200
        encargo.descripcion = "Encargo de lavado que incluye todo"

        btn_plus.setOnClickListener{
            add()
        }

        btn_less.setOnClickListener{
           subtraction()
        }

        btn_cancelar.setOnClickListener {
            finish()
        }

        btn_aceptar.setOnClickListener{
            nuevoEncargo()
        }

        tv_total.setText(getString(R.string.total) + "$" + 0)

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            selectedDate = SimpleDateFormat("dd/MM/yyyy").format(calendar.time)
        }

        et_cant.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var total: Int = s.toString().toInt()
                total = total * 200
                tv_total.setText(getString(R.string.total) + "$" + total)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun nuevoEncargo() {
        if((et_nombre.text.isEmpty()) ||
            (et_cant.text.toString().toInt() == 0)
        ) {
            Toast.makeText(this, "Favor de completer todos los campos", Toast.LENGTH_SHORT).show()
            return
        }


        encargo.setCantidad(et_cant.text.toString().toInt())

        val fechaHoy: LocalDate = LocalDate.now()

        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val fechaFormateada = fechaHoy.format(formato)

        val total = encargo.getCantidad() * encargo.precio

        var productos: MutableList<ProductoNota> = mutableListOf()
        productos.add(encargo)


        val nota = Nota(productos, fechaFormateada, selectedDate, total, et_nombre.text.toString(), "Usuario que vende", Random.nextInt(1, 9999))

        MainActivity.notasEncargo.add(nota)
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun subtraction() {
        var cant: Int = et_cant.text.toString().toInt()
        cant--
        if(cant < 0)
        {
            cant = 0
        }
        et_cant.setText(cant.toString())
    }

    private fun add() {
        var cant: Int = et_cant.text.toString().toInt()
        cant++
        et_cant.setText(cant.toString())
    }
}