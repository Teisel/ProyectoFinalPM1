package com.example.proyectofinalpm1

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.random.Random

class TintoActivity : AppCompatActivity() {

    private lateinit var et_nombre: EditText
    private lateinit var et_cant: EditText
    private lateinit var btn_plus: Button
    private lateinit var btn_less: Button
    private lateinit var btn_aceptar: Button
    private lateinit var btn_cancelar: Button
    private lateinit var calendar: CalendarView
    private lateinit var tv_total: TextView
    private var selectedDate: String = ""
    private lateinit var spinner: Spinner

    private var encargo: ProductoNota = ProductoNota(0)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tintoreria)

        et_nombre = findViewById(R.id.et_nombreEncargo_tinto)
        et_cant = findViewById(R.id.et_cantEncargo_tinto)
        btn_plus = findViewById(R.id.btn_plus_tinto)
        btn_less = findViewById(R.id.btn_less_tinto)
        btn_aceptar = findViewById(R.id.btn_aceptar_tinto)
        btn_cancelar = findViewById(R.id.btn_cancelar_tinto)
        calendar = findViewById(R.id.calendarView)
        tv_total = findViewById(R.id.tv_total_tinto)

        spinner = findViewById(R.id.s_tinto)
        val items = arrayOf("Plancha", "Lavado en seco", "Desmanchado")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        encargo.nombre = "Encargo de tintoreria"
        encargo.setCantidad(0)
        encargo.tipo = "T"
        encargo.precio = 200
        encargo.descripcion = "Encargo de tintoreria que depende de la necesidad del cliente"

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
                var precio = 0
                if(spinner.selectedItem.toString().equals("Plancha"))
                {
                    precio = 17
                }
                if(spinner.selectedItem.toString().equals("Lavado en seco"))
                {
                    precio = 50
                }
                if(spinner.selectedItem.toString().equals("Desmanchado"))
                {
                    precio = 35
                }
                total = total * precio
                tv_total.setText(getString(R.string.total) + "$" + total)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                var precio = 0
                if (selectedItem.equals("Plancha")) {
                    precio = 17
                }
                if (selectedItem.equals("Lavado en seco")) {
                    precio = 50
                }
                if (selectedItem.equals("Desmanchado")) {
                    precio = 35
                }
                var cant = et_cant.text.toString().toInt()

                var total = cant * precio
                tv_total.setText(getString(R.string.total) + "$" + total)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                println("No se ha seleccionado ninguna opción.")
            }
        }

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

        MainActivity.notasTintoreria.add(nota)
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