package com.akihabara.market.view;

import com.akihabara.market.model.ProductoOtaku;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InterfazConsola extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField nombreField = new JTextField(15);
    private final JTextField categoriaField = new JTextField(15);
    private final JTextField precioField = new JTextField(15);
    private final JTextField stockField = new JTextField(15);
    private final JTextField idField = new JTextField(5);
    private final DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Categoría", "Precio", "Stock"}, 0);
    public final JTable tabla = new JTable(tableModel);

    public InterfazConsola() {
        setTitle("Gestión de Productos Otaku");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Formulario de Producto"));
        formPanel.add(new JLabel("ID (para actualizar/eliminar):"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Categoría:"));
        formPanel.add(categoriaField);
        formPanel.add(new JLabel("Precio:"));
        formPanel.add(precioField);
        formPanel.add(new JLabel("Stock:"));
        formPanel.add(stockField);

        JPanel buttonPanel = new JPanel();
        JButton agregarBtn = new JButton("Agregar");
        JButton actualizarBtn = new JButton("Actualizar");
        JButton eliminarBtn = new JButton("Eliminar");
        JButton listarBtn = new JButton("Listar Todo");
        buttonPanel.add(agregarBtn);
        buttonPanel.add(actualizarBtn);
        buttonPanel.add(eliminarBtn);
        buttonPanel.add(listarBtn);

        JScrollPane scrollPane = new JScrollPane(tabla);

        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);

        // Eventos de botones
        agregarBtn.addActionListener(e -> MainApp.agregarProducto());
        actualizarBtn.addActionListener(e -> MainApp.actualizarProducto());
        eliminarBtn.addActionListener(e -> MainApp.eliminarProducto());
        listarBtn.addActionListener(e -> MainApp.listarProductos());
    }

    // Métodos para acceso desde el controlador
    public String getNombre() { return nombreField.getText(); }
    public String getCategoria() { return categoriaField.getText(); }
    public String getPrecio() { return precioField.getText(); }
    public String getStock() { return stockField.getText(); }
    public String getId() { return idField.getText(); }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void limpiarTabla() {
        tableModel.setRowCount(0);
    }

    public void mostrarProductos(List<ProductoOtaku> productos) {
        limpiarTabla();
        for (ProductoOtaku p : productos) {
            tableModel.addRow(new Object[]{p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock()});
        }
    }
}
