package com.example.proyectofinalpm1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    companion object {
        fun agregarNotaEncanrgo(nota: Nota) {
            notasEncargo.add(nota)
        }

        val notasEncargo: MutableList<Nota> = mutableListOf()

        val notasTintoreria: MutableList<Nota> = mutableListOf()

        val insumosLavanderia = mutableListOf(
            Insumo(cantidad = 50, nombre = "Detergente"),
            Insumo(cantidad = 30, nombre = "Suavizante"),
            Insumo(cantidad = 20, nombre = "Blanqueador"),
            Insumo(cantidad = 100, nombre = "Jabón en barra"),
            Insumo(cantidad = 10, nombre = "Desinfectante"),
            Insumo(cantidad = 5, nombre = "Bolsa para ropa delicada"),
        )

        var maquinas: List<Maquinas> = listOf(
            Maquinas(0,false),
            Maquinas(0,false),
            Maquinas(0,false),
            Maquinas(0,false),
            Maquinas(0,false),
            Maquinas(0,false)
        )

        var empleados = mutableListOf(Empleado(1,10230.23,"si","si","si","A"))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.login)

        etLogin = findViewById(R.id.et_login)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        empleados[0].nombre = "Teisel"
        empleados[0].pass = "12345"
        empleados[0].correo = "teisel@gmail.com"
        empleados[0].telefono = "3315190956"

        val insumosLavanderia = mutableListOf(
            Insumo(cantidad = 50, nombre = "Detergente"),
            Insumo(cantidad = 30, nombre = "Suavizante"),
            Insumo(cantidad = 20, nombre = "Blanqueador"),
            Insumo(cantidad = 100, nombre = "Jabón en barra"),
            Insumo(cantidad = 10, nombre = "Desinfectante"),
            Insumo(cantidad = 5, nombre = "Bolsa para ropa delicada"),
        )

        btnLogin.setOnClickListener{
            logIn()
        }

    }

    private fun logIn() {
        var user = etLogin.text.toString()
        var pass = etPassword.text.toString()

        if((user.isEmpty()) || (pass.isEmpty()) )
        {
            Toast.makeText(this, "Favor de completer todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        if(empleados.find { it.nombre.equals(user) } != null)
        {
            var empleado = empleados.find { it.nombre.equals(user) }
            if (empleado != null) {
                if (empleado.pass.equals(pass)) {
                    if (empleado.getTipo().equals("A")){
                        val intent = Intent(this, MenuAdminActtivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        val intent = Intent(this, MenuActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
        }
        else{
            Toast.makeText(this, "Contraseña o usuario incorrectos", Toast.LENGTH_SHORT).show()
            return
        }

    }
}