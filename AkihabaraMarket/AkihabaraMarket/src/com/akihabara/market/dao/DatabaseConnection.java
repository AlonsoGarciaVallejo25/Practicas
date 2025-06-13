package com.akihabara.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    // 1. Variables constantes para conexión
    private static final String URL = "jdbc:mysql://localhost:3306/akihabara_db";
    private static final String USER = "user_Akihabara";
    private static final String PASSWORD = "12345";

    // Cargar el driver una vez al inicio
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Se ha cargado en memoria el driver de MySQL.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver de MySQL: " + e.getMessage());
        }
    }

    // Método para obtener conexión
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Se ha establecido con éxito la conexión a la base de datos.");
        return conn;
    }
}
