package com.mycompany.restauranteelbuensabor;

// resultado del cálculo, solo datos, no tiene lógica

public class Factura {
    private final int numero;
    private final double subtotalBase;
    private final double descuento;
    private final double iva;
    private final double propina;
    private final double total;

    public Factura(int numero, double subtotalBase, double descuento, double iva, double propina, double total) {
        this.numero = numero;
        this.subtotalBase = subtotalBase;
        this.descuento = descuento;
        this.iva = iva;
        this.propina = propina;
        this.total = total;
    }

    public int getNumero() { return numero; }
    public double getSubtotalBase() { return subtotalBase; }
    public double getDescuento() { return descuento; }
    public double getIva() { return iva; }
    public double getPropina() { return propina; }
    public double getTotal() { return total; }
}