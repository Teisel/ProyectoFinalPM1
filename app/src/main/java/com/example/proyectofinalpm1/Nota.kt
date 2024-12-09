package com.example.proyectofinalpm1

import java.io.Serializable

data class Nota (
    var productos: MutableList<ProductoNota> = mutableListOf(),
    var fechaPago: String,
    var fechaEntrega: String,
    var total: Int,
    var nombreUsuaario: String,
    var nombreEncargado: String,
    var idNota: Int
) : Serializable