package com.example.alquilercocheras.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:cocheras.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

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

        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

}
