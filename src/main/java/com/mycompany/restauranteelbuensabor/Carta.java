package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

// lista fija de productos que vende el restaurante

public class Carta {
    private final List<Producto> productos;

    public Carta() {
        productos = new ArrayList<>();// cargar productos (podría venir de base de datos en el futuro)
        productos.add(new Producto(1, "Bandeja Paisa", 32000));
        productos.add(new Producto(2, "Sancocho de Gallina", 28000));
        productos.add(new Producto(3, "Arepa con Huevo", 8000));
        productos.add(new Producto(4, "Jugo Natural", 7000));
        productos.add(new Producto(5, "Gaseosa", 4500));
        productos.add(new Producto(6, "Cerveza Poker", 6000));
        productos.add(new Producto(7, "Agua Panela", 3500));
        productos.add(new Producto(8, "Arroz con Pollo", 25000));
    }

    public List<Producto> getProductos() { return new ArrayList<>(productos); }// devuelve copia para que no modifiquen la original
    public Producto buscarPorId(int id) {  // busca por el número que ve el usuario  
        for (Producto p : productos) if (p.getId() == id) return p;
        return null;
    }
    public int getCantidad() { return productos.size(); }
}