package com.akihabara.market.view;

import com.akihabara.market.model.ProductoOtaku;

import java.util.List;
import java.util.Scanner;

public class InterfazConsola {

    private final Scanner scanner;

    public InterfazConsola() {
        this.scanner = new Scanner(System.in);
    }

    // Mostrar el menú principal
    public void mostrarMenu() {
        System.out.println("===== Menú Principal =====");
        System.out.println("1. Añadir producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Listar productos por nombre");
        System.out.println("5. Listar productos por categoría");
        System.out.println("6. Actualizar producto");
        System.out.println("7. Eliminar producto");
        System.out.println("8. Salir");
        System.out.print("Elige una opción: ");
    }

    // Leer y validar la opción del menú
    public int leerOpcion() {
        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= 8) {
                    return opcion;
                } else {
                    System.out.print("Opción no válida. Por favor, elige entre 1 y 8: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Por favor, ingresa un número: ");
            }
        }
    }

    // Pedir datos para agregar un producto
    public ProductoOtaku pedirDatosProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría del producto: ");
        String categoria = scanner.nextLine();
        System.out.print("Precio del producto: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Stock del producto: ");
        int stock = Integer.parseInt(scanner.nextLine());

        return new ProductoOtaku(stock, nombre, categoria, precio, stock);
    }

    // Pedir ID del producto
    public int pedirIdProducto() {
        System.out.print("Ingresa el ID del producto: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Pedir nombre del producto
    public String pedirNombreProducto() {
        System.out.print("Ingresa el nombre del producto: ");
        return scanner.nextLine();
    }

    // Pedir categoría del producto
    public String pedirCategoriaProducto() {
        System.out.print("Ingresa la categoría del producto: ");
        return scanner.nextLine();
    }

    // Mostrar productos
    public void mostrarProductos(List<ProductoOtaku> productos) {
        if (productos.isEmpty()) {
            System.out.println("No se encontraron productos.");
        } else {
            for (ProductoOtaku producto : productos) {
                System.out.println(producto.getId() + " - " + producto.getNombre() + " | " + producto.getCategoria() + " | $" + producto.getPrecio() + " | Stock: " + producto.getStock());
            }
        }
    }

    // Mostrar mensaje de confirmación
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Mostrar mensaje de error
    public void mostrarError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }
}