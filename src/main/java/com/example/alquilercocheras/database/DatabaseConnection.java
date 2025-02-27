package com.example.alquilercocheras.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:cocheras.db";
    private static Connection instance = null;

    // Constructor privado para evitar instancias múltiples
    private DatabaseConnection() {}

    // Obtener la única instancia de la conexión
    public static Connection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    try {
                        // Cargar el driver manualmente
                        Class.forName("org.sqlite.JDBC");

                        instance = DriverManager.getConnection(URL);
                        System.out.println("✅ Conectado a SQLite correctamente.");
                    } catch (ClassNotFoundException e) {
                        System.out.println("❌ Driver SQLite no encontrado: " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("❌ Error al conectar con SQLite: " + e.getMessage());
                    }
                }
            }
        }
        return instance;
    }

    // Método para cerrar la conexión cuando ya no se use
    public static void closeConnection() {
        if (instance != null) {
            try {
                instance.close();
                instance = null;
                System.out.println("🔴 Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("❌ Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
