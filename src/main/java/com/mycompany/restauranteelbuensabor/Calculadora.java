package com.mycompany.restauranteelbuensabor;

// contiene las reglas de negocio: descuento, iva, propina

public class Calculadora {
    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_PRODUCTOS_DESCUENTO = 3;
    private static int ultimoNumeroFactura = 1;

    public Factura generarFactura(Pedido pedido) {
        double subtotalBase = pedido.calcularSubtotalBase(); // 1. calcular subtotal base (precio * cantidad)
        int productosDif = pedido.contarProductosDiferentes();

        double descuento = 0;
        if (productosDif > MIN_PRODUCTOS_DESCUENTO) {        // 2. aplicar descuento si corresponde
            descuento = subtotalBase * TASA_DESCUENTO;
        }
        double subtotalConDescuento = subtotalBase - descuento;

        double iva = subtotalConDescuento * TASA_IVA;         // 3. calcular iva sobre el subtotal ya descontado

        double propina = 0;
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            double conIva = subtotalConDescuento + iva;       // 4. propina solo si el subtotal (con descuento) supera el umbral
            propina = conIva * TASA_PROPINA;
        }

        double total = subtotalConDescuento + iva + propina;  // 5. total final

        Factura factura = new Factura(ultimoNumeroFactura++, subtotalBase, descuento, iva, propina, total);
        pedido.cerrar();        // esta factura ya no se puede modificar
        return factura;
    }
}
