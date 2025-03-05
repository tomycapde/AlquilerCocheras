package com.example.alquilercocheras.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseManager {

    // Inicializa la base de datos
    public static void initializeDatabase() {
        Connection conn = DatabaseConnection.getInstance();
        if (conn == null) {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
            return;
        }

        try (Statement stmt = conn.createStatement()) {
            // Sentencias para crear las tablas si no existen
            String sql = """
                CREATE TABLE IF NOT EXISTS TipoEstadia (
                    idTipoEstadia INTEGER PRIMARY KEY AUTOINCREMENT,
                    descripcion TEXT NOT NULL UNIQUE
                );

                CREATE TABLE IF NOT EXISTS Cliente (
                    idCliente INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    dni TEXT UNIQUE NOT NULL,
                    telefono TEXT NOT NULL
                );

                CREATE TABLE IF NOT EXISTS TipoVehiculo (
                    idTipoVehiculo INTEGER PRIMARY KEY AUTOINCREMENT,
                    descripcion TEXT NOT NULL UNIQUE
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
                    descripcion TEXT NOT NULL UNIQUE,
                    precio REAL NOT NULL
                );

                CREATE TABLE IF NOT EXISTS TipoUsuario (
                    idTipoUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                    descripcion TEXT NOT NULL UNIQUE
                );

                CREATE TABLE IF NOT EXISTS Usuario (
                    idUsuario INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    contrasenia TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    dni TEXT UNIQUE NOT NULL,
                    telefono TEXT NULL,
                    idTipoUsuario INTEGER NOT NULL,
                    FOREIGN KEY (idTipoUsuario) REFERENCES TipoUsuario(idTipoUsuario)
                );
                """;

            stmt.executeUpdate(sql);
            System.out.println("Base de datos inicializada correctamente.");

            insertBasicData(conn);

        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    // Inserta datos básicos si las tablas están vacías
    private static void insertBasicData(Connection conn) {
        try (Statement stmt = conn.createStatement()) {

            if (isTableEmpty(conn, "TipoEstadia")) {
                stmt.executeUpdate("INSERT INTO TipoEstadia (descripcion) VALUES ('Mensual');");
                System.out.println("Datos insertados en TipoEstadia.");
            }

            if (isTableEmpty(conn, "TipoVehiculo")) {
                stmt.executeUpdate("INSERT INTO TipoVehiculo (descripcion) VALUES ('Auto'), ('Moto'), ('Camioneta'), ('Bicicleta');");
                System.out.println("Datos insertados en TipoVehiculo.");
            }

            if (isTableEmpty(conn, "TipoUsuario")) {
                stmt.executeUpdate("INSERT INTO TipoUsuario (descripcion) VALUES ('Administrador'), ('Usuario');");
                System.out.println("Datos insertados en TipoUsuario.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar datos básicos: " + e.getMessage());
        }
    }

    // Verifica si una tabla está vacía
    private static boolean isTableEmpty(Connection conn, String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            return rs.next() && rs.getInt(1) == 0;
        } catch (SQLException e) {
            System.out.println("Error al verificar si la tabla " + tableName + " está vacía: " + e.getMessage());
            return false;
        }
    }

    public static boolean databaseHasUsers() {
        Connection conn = DatabaseConnection.getInstance();
        if (conn == null) {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
            return false;
        }

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Usuario")) {
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Error al verificar si la base de datos tiene usuarios: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        initializeDatabase();
    }
}
