package com.mycompany.restauranteelbuensabor;
    
// punto de entrada del programa

public class RestauranteElBuenSabor {
    public static void main(String[] args) {
        // crear los componentes necesarios
        Carta carta = new Carta();
        GestorPedido gestorPedido = new GestorPedido();
        Calculadora calculadora = new Calculadora();
        Impresion impresion = new Impresion();
         // iniciar el menú interactivo
        MenuConsola menu = new MenuConsola(carta, gestorPedido, calculadora, impresion);
        menu.iniciar();
    }
}