package com.akihabara.market.view;

import com.akihabara.market.model.ProductoOtaku;

import IA.Llm;

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
        System.out.println("8. Generar nombre con IA");
        System.out.println("9. Salir");
        System.out.print("Elige una opción: ");
    }

    // Leer y validar la opción del menú
    public int leerOpcion() {
        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= 9) {
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
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    private int pedirEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Intente de nuevo.");
            }
        }
    }

    private double pedirDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número decimal inválido. Intente de nuevo.");
            }
        }
    }

    public ProductoOtaku pedirDatosIA() {
        String nombre;

        System.out.print("¿Desea generar el nombre con IA? (s/n): ");
        String usarIA = scanner.nextLine().trim().toLowerCase();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        
        System.out.print("franquicia: ");
        String franquicia = scanner.nextLine();

        if (usarIA.equals("s")) {
        	Llm conexionAPI = new Llm();
            System.out.println("Generando nombre sugerido con IA...");
            nombre = conexionAPI.sugerirNombreProducto(franquicia, categoria);
            System.out.println(" Nombre sugerido: " + nombre);
        } else {
            System.out.print("Nombre: ");
            nombre = scanner.nextLine();
        }
        System.out.print("Precio: ");
        
        double precio = pedirDouble("Precio: ");
        int stock = pedirEntero("Stock: ");

        return new ProductoOtaku(0, nombre, categoria, precio, stock);

    }

    public void mostrarError(String mensaje) {
        System.err.println("Error: " + mensaje);
    }

}