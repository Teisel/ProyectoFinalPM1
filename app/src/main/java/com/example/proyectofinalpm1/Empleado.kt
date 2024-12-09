package com.example.proyectofinalpm1

class Empleado(
    private var idEmpleado: Int,
    private var salario: Double,
    private var inicioTurn: String,
    private var finTurno: String,
    private var fechaContratacion: String,
    private var tipo: String
): Persona() {
    fun getTipo(): String {
        return tipo
    }

    fun getidEmpleado(): Int{
        return idEmpleado
    }

    fun getHE(): String {
        return inicioTurn
    }
    fun getHS(): String {
        return finTurno
    }

}