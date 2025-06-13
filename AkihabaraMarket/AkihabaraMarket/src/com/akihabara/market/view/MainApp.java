package com.akihabara.market.view;

import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;


import javax.swing.*;
import java.util.List;

public class MainApp {

    private static final ProductoDAO dao = new ProductoDAO();
    private static final InterfazConsola vista = new InterfazConsola();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> vista.setVisible(true));
    }

    public static void agregarProducto() {
        try {
            ProductoOtaku p = new ProductoOtaku(
                0, vista.getNombre(),
                vista.getCategoria(),
                Double.parseDouble(vista.getPrecio()),
                Integer.parseInt(vista.getStock())
            );
            dao.agregarProducto(p);
            vista.mostrarMensaje("Producto agregado.");
        } catch (Exception e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    public static void actualizarProducto() {
        try {
            ProductoOtaku p = new ProductoOtaku(
                Integer.parseInt(vista.getId()),
                vista.getNombre(),
                vista.getCategoria(),
                Double.parseDouble(vista.getPrecio()),
                Integer.parseInt(vista.getStock())
            );
            if (dao.actualizarProducto(p)) {
                vista.mostrarMensaje("Producto actualizado.");
            } else {
                vista.mostrarMensaje("No se encontró el producto.");
            }
        } catch (Exception e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    public static void eliminarProducto() {
        try {
            int id = Integer.parseInt(vista.getId());
            if (dao.eliminarProducto(id)) {
                vista.mostrarMensaje("Producto eliminado.");
            } else {
                vista.mostrarMensaje("No se encontró el producto.");
            }
        } catch (Exception e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    public static void listarProductos() {
        List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
        vista.mostrarProductos(productos);
    }
}
