package com.akihabara.market.view;

import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        ProductoDAO productoDAO = new ProductoDAO();
        InterfazConsola vista = new InterfazConsola();
        boolean salir = false;

        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();

            switch (opcion) {
                case 1: // Añadir producto
                    ProductoOtaku nuevoProducto = vista.pedirDatosProducto();
                    productoDAO.agregarProducto(nuevoProducto);
                    vista.mostrarMensaje("Producto agregado con éxito.");
                    break;

                case 2: // Consultar producto por ID
                    int idConsultar = vista.pedirIdProducto();
                    ProductoOtaku producto = productoDAO.obtenerProductoPorId(idConsultar);
                    if (producto != null) {
                        vista.mostrarMensaje("Producto encontrado: " + producto.getNombre());
                    } else {
                        vista.mostrarError("Producto no encontrado.");
                    }
                    break;

                case 3: // Listar todos los productos
                    List<ProductoOtaku> productos = productoDAO.obtenerTodosLosProductos();
                    vista.mostrarProductos(productos);
                    break;

                case 4: // Listar productos por nombre
                    String nombre = vista.pedirNombreProducto();
                    List<ProductoOtaku> productosPorNombre = productoDAO.buscarProductosPorNombre(nombre);
                    vista.mostrarProductos(productosPorNombre);
                    break;

                case 5: // Listar productos por categoría
                    String categoria = vista.pedirCategoriaProducto();
                    List<ProductoOtaku> productosPorCategoria = productoDAO.buscarProductoPorCategoria(categoria);
                    vista.mostrarProductos(productosPorCategoria);
                    break;

                case 6: // Actualizar producto
                    int idActualizar = vista.pedirIdProducto();
                    ProductoOtaku productoActualizar = productoDAO.obtenerProductoPorId(idActualizar);
                    if (productoActualizar != null) {
                        ProductoOtaku nuevosDatos = vista.pedirDatosProducto();
                        nuevosDatos.setId(idActualizar);
                        boolean actualizado = productoDAO.actualizarProducto(nuevosDatos);
                        if (actualizado) {
                            vista.mostrarMensaje("Producto actualizado con éxito.");
                        } else {
                            vista.mostrarError("Error al actualizar producto.");
                        }
                    } else {
                        vista.mostrarError("Producto no encontrado.");
                    }
                    break;

                case 7: // Eliminar producto
                    int idEliminar = vista.pedirIdProducto();
                    boolean eliminado = productoDAO.eliminarProducto(idEliminar);
                    if (eliminado) {
                        vista.mostrarMensaje("Producto eliminado con éxito.");
                    } else {
                        vista.mostrarError("Error al eliminar producto.");
                    }
                    break;

                case 8: // Salir
                    salir = true;
                    vista.mostrarMensaje("¡Hasta luego!");
                    break;

                default:
                    vista.mostrarError("Opción no válida.");
                    break;
            }
        }
    }
}