package com.mycompany.restauranteelbuensabor;

// un producto con su cantidad pedida

public class ItemPedido {
    private final Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public void agregarCantidad(int adicional) { this.cantidad += adicional; }
    public double getSubtotal() { return producto.getPrecio() * cantidad; }
}