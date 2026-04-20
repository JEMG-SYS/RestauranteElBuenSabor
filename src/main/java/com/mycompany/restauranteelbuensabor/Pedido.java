package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

// pedido actual de una mesa

public class Pedido {
    private final int numeroMesa;
    private final List<ItemPedido> items;
    private boolean activo;  // si ya se facturó, ya no se puede modificar

    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.items = new ArrayList<>();
        this.activo = true;
    }

    public int getNumeroMesa() { return numeroMesa; }
    public boolean isActivo() { return activo; }
    public void cerrar() { this.activo = false; }

                
    public void agregarProducto(Producto producto, int cantidad) {
        for (ItemPedido item : items) {                              // añade o incrementa un producto en el pedido
            if (item.getProducto().getId() == producto.getId()) {
                item.agregarCantidad(cantidad);
                return;
            }
        }
        items.add(new ItemPedido(producto, cantidad));
    }

    public List<ItemPedido> getItems() { return new ArrayList<>(items); }
    public boolean estaVacio() {
        for (ItemPedido item : items) {
            if (item.getCantidad() > 0) return false;
        }
        return true;
    }
    public void reiniciar() { items.clear(); activo = true; }
    public int contarProductosDiferentes() { return items.size(); }// cuantos productos diferentes tiene (para saber si aplica descuento)
    public double calcularSubtotalBase() {
        double subtotal = 0;     // suma de todos los subtotales sin descuentos
        for (ItemPedido item : items) subtotal += item.getSubtotal();
        return subtotal;
    }
}