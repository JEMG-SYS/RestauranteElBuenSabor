package com.mycompany.restauranteelbuensabor;

// maneja el pedido actual (la mesa que está atendiendo)

public class GestorPedido {
    private Pedido pedidoActual;

    public void iniciarNuevaMesa(int numeroMesa) {
        pedidoActual = new Pedido(numeroMesa);
    }

    public boolean hayPedidoActivo() {
        return pedidoActual != null && pedidoActual.isActivo();
    }

    public Pedido getPedidoActual() { return pedidoActual; }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0 || producto == null) return false;
        if (pedidoActual == null) return false;
        pedidoActual.agregarProducto(producto, cantidad);
        return true;
    }

    public void reiniciarPedido() {
        if (pedidoActual != null) pedidoActual.reiniciar();
    }
}