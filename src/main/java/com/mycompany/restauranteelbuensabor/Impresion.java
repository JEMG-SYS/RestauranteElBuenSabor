package com.mycompany.restauranteelbuensabor;

import java.util.List;

// todo lo que se muestra en pantalla

public class Impresion {
    private static final String SEPARADOR = "========================================";
    private static final String SEPARADOR_CORTO = "----------------------------------------";
    private static final String NOMBRE_RESTAURANTE = "RESTAURANTE EL BUEN SABOR";
    private static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    private static final String NIT = "NIT: 900.123.456-7";

    public void mostrarEncabezado() {
        System.out.println(SEPARADOR);
        System.out.println("    " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    " + NIT);
        System.out.println(SEPARADOR);
    }

    public void mostrarCarta(List<Producto> productos) {
        System.out.println(SEPARADOR);
        System.out.println("    " + NOMBRE_RESTAURANTE);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR);
        for (Producto p : productos) {
            System.out.printf("%d. %-22s $%,.0f%n", p.getId(), p.getNombre(), p.getPrecio());
        }
        System.out.println(SEPARADOR);
    }

    public void mostrarPedidoActual(Pedido pedido) {
        if (pedido == null || pedido.estaVacio()) {
            System.out.println("No hay productos en el pedido actual.");
            return;
        }
        System.out.println("--- PEDIDO ACTUAL ---");
        double subtotal = 0;
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n",
                item.getProducto().getNombre(), item.getCantidad(), item.getSubtotal());
            subtotal += item.getSubtotal();
        }
        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public void mostrarFactura(Factura factura, Pedido pedido) {
        mostrarEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        System.out.println(SEPARADOR_CORTO);
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n",
                item.getProducto().getNombre(), item.getCantidad(), item.getSubtotal());
        }
        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.getSubtotalBase());
        if (factura.getDescuento() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Descuento (5%):", factura.getDescuento());
        }
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", factura.getIva());
        if (factura.getPropina() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", factura.getPropina());
        }
        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.getTotal());
        System.out.println(SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println(NOMBRE_RESTAURANTE + " - Valledupar");
        System.out.println(SEPARADOR);
    }
}