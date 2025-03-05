package com.example.alquilercocheras.database;

import com.example.alquilercocheras.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public static void registerClient(Client client) {
        if (clientExists(client.getDni())) {
            System.out.println("El cliente ya existe");
            throw new IllegalArgumentException("El cliente ya existe");
        }
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement("INSERT INTO Cliente (nombre, apellido, dni, telefono) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getDni());
            preparedStatement.setString(4, client.getPhone());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Cliente registrado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Cliente");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setIdClient(rs.getInt("idCliente"));
                client.setName(rs.getString("nombre"));
                client.setLastName(rs.getString("apellido"));
                client.setDni(rs.getString("dni"));
                client.setPhone(rs.getString("telefono"));
                clients.add(client);
            }
            preparedStatement.close();
            return clients;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static boolean clientExists(String dni) {
        String query = "SELECT COUNT(*) FROM Cliente WHERE dni = ?";
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, dni);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

