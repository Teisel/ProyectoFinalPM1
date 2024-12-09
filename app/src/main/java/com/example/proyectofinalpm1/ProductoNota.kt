package com.example.proyectofinalpm1

class ProductoNota(
    private var cantidad: Int
): Producto()
{
    fun getCantidad(): Int {
        return this.cantidad
    }

    /////////////////////////////

    fun setCantidad(cantidad: Int){
        this.cantidad = cantidad
    }


}


