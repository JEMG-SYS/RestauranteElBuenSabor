package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

// controla el bucle del menú y coordina las acciones

public class MenuConsola {
    private final Scanner scanner;
    private final Carta carta;
    private final GestorPedido gestorPedido;
    private final Calculadora calculadora;
    private final Impresion impresion;
    private boolean ejecutando;

    public MenuConsola(Carta carta, GestorPedido gestorPedido,
                       Calculadora calculadora, Impresion impresion) {
        this.scanner = new Scanner(System.in);
        this.carta = carta;
        this.gestorPedido = gestorPedido;
        this.calculadora = calculadora;
        this.impresion = impresion;
        this.ejecutando = true;
    }

    public void iniciar() {  // bucle principal hasta que el usuario elija salir
        impresion.mostrarEncabezado();
        while (ejecutando) {
            mostrarMenu();
            int opcion = leerEntero();
            procesarOpcion(opcion);
            System.out.println();
        }
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }

    private int leerEntero() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.next();
            return -1;
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: verCarta(); break;
            case 2: agregarProducto(); break;
            case 3: verPedidoActual(); break;
            case 4: generarFactura(); break;
            case 5: nuevaMesa(); break;
            case 0: salir(); break;
            default: System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
        }
    }

    private void verCarta() {
        impresion.mostrarCarta(carta.getProductos());
    }

    private void agregarProducto() {
        System.out.println("--- AGREGAR PRODUCTO ---");
        System.out.print("Numero de producto (1-" + carta.getCantidad() + "): ");
        int num = leerEntero();                     // si no hay mesa activa, pide número de mesa
        System.out.print("Cantidad: ");             // luego agrega o incrementa el producto 
        int cant = leerEntero();

        if (num < 1 || num > carta.getCantidad()) {
            System.out.println("Producto no existe.");
            return;
        }
        if (cant <= 0) {
            System.out.println("La cantidad debe ser mayor a cero.");
            return;
        }

        if (!gestorPedido.hayPedidoActivo()) {
            System.out.print("Ingrese numero de mesa: ");
            int mesa = leerEntero();
            if (mesa <= 0) mesa = 1;
            gestorPedido.iniciarNuevaMesa(mesa);
        }

        Producto producto = carta.buscarPorId(num);
        boolean ok = gestorPedido.agregarProducto(producto, cant);
        if (ok) {
            System.out.println("Producto agregado al pedido.");
            System.out.println("  -> " + producto.getNombre() + " x" + cant);
        } else {
            System.out.println("Error al agregar producto.");
        }
    }

    private void verPedidoActual() {
        if (!gestorPedido.hayPedidoActivo() || gestorPedido.getPedidoActual().estaVacio()) {
            System.out.println("No hay productos en el pedido actual.");
            System.out.println("Use la opcion 2 para agregar productos.");
        } else {
            impresion.mostrarPedidoActual(gestorPedido.getPedidoActual());
        }
    }

    private void generarFactura() {      // solo si hay productos, calcula y muestra la factura
        if (!gestorPedido.hayPedidoActivo() || gestorPedido.getPedidoActual().estaVacio()) {
            System.out.println("No se puede generar factura.");
            System.out.println("No hay productos en el pedido.");
            System.out.println("Use la opcion 2 para agregar productos primero.");
            return;
        }
        Pedido pedido = gestorPedido.getPedidoActual();
        Factura factura = calculadora.generarFactura(pedido);
        impresion.mostrarFactura(factura, pedido);
    }

    private void nuevaMesa() {
        gestorPedido.reiniciarPedido();
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
    }

    private void salir() {
        ejecutando = false;
        System.out.println("Hasta luego!");
    }
}