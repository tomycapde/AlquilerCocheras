package com.example.alquilercocheras.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseManager {

    // Initialize Database
    public static void initializeDatabase() {
        Connection conn = DatabaseConnection.getInstance();
        if (conn == null) {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
            return;
        }

        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            // Sentencias para crear las tablas si no existen
            String sql = """
                CREATE TABLE IF NOT EXISTS TipoEstadia (
                    idTipoEstadia INTEGER PRIMARY KEY AUTOINCREMENT,
                    descripcion TEXT NOT NULL
                );

                CREATE TABLE IF NOT EXISTS Cliente (
                    idCliente INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    dni TEXT UNIQUE NOT NULL
                );

                CREATE TABLE IF NOT EXISTS TipoVehiculo (
                    idTipoVehiculo INTEGER PRIMARY KEY AUTOINCREMENT,
                    descripcion TEXT NOT NULL
                );

                CREATE TABLE IF NOT EXISTS Vehiculo (
                    idVehiculo INTEGER PRIMARY KEY AUTOINCREMENT,
                    idCliente INTEGER NOT NULL,
                    tipoVehiculo INTEGER NOT NULL,
                    patente TEXT UNIQUE NOT NULL,
                    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
                    FOREIGN KEY (tipoVehiculo) REFERENCES TipoVehiculo(idTipoVehiculo)
                );

                CREATE TABLE IF NOT EXISTS Estadia (
                    idEstadia INTEGER PRIMARY KEY AUTOINCREMENT,
                    fechaInicio DATE NOT NULL,
                    fechaFin DATE NOT NULL,
                    idCliente INTEGER NOT NULL,
                    idVehiculo INTEGER NOT NULL,
                    idTipoEstadia INTEGER NOT NULL,
                    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
                    FOREIGN KEY (idVehiculo) REFERENCES Vehiculo(idVehiculo),
                    FOREIGN KEY (idTipoEstadia) REFERENCES TipoEstadia(idTipoEstadia)
                );

                CREATE TABLE IF NOT EXISTS Precio (
                    idPrecio INTEGER PRIMARY KEY AUTOINCREMENT,
                    descripcion TEXT NOT NULL,
                    precio REAL NOT NULL
                );

                CREATE TABLE IF NOT EXISTS TipoUsuario (
                    idTipoUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                    descripcion TEXT NOT NULL
                );

                CREATE TABLE IF NOT EXISTS Usuario (
                    idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    dni TEXT UNIQUE NOT NULL,
                    idTipoUsuario INTEGER NOT NULL,
                    FOREIGN KEY (idTipoUsuario) REFERENCES TipoUsuario(idTipoUsuario)
                );
                """;

            stmt.executeUpdate(sql);

            System.out.println("Base de datos inicializada correctamente.");

            insertBasicData();

        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el Statement: " + e.getMessage());
            }
        }
    }

    // Load Basic Data
    private static void insertBasicData() {
        Connection conn = DatabaseConnection.getInstance();
        if (conn == null) {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
            return;
        }

        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            // Sentencias para insertar datos básicos
            String sql = """
                INSERT INTO TipoEstadia (descripcion) VALUES ('Mensual');

                INSERT INTO TipoVehiculo (descripcion) VALUES ('Auto');
                INSERT INTO TipoVehiculo (descripcion) VALUES ('Moto');
                INSERT INTO TipoVehiculo (descripcion) VALUES ('Camioneta');
                INSERT INTO TipoVehiculo (descripcion) VALUES ('Bicicleta');

                INSERT INTO TipoUsuario (descripcion) VALUES ('Administrador');
                INSERT INTO TipoUsuario (descripcion) VALUES ('Usuario');
                """;

            stmt.executeUpdate(sql);

            System.out.println("Datos básicos insertados correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar datos básicos: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el Statement: " + e.getMessage());
            }
        }
    }

    // Verify if the database has users
    public static boolean databaseHasUsers() {
        Connection conn = DatabaseConnection.getInstance();
        if (conn == null) {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
            return false;
        }

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM Usuario");

            return rs.next() && rs.getInt(1) > 0;

        } catch (SQLException e) {
            System.out.println("Error al verificar si la base de datos tiene usuarios: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }



    public static void main(String[] args) {
        initializeDatabase();
    }
}
