package com.example.alquilercocheras.database;

import com.example.alquilercocheras.models.User;

import java.sql.*;

public class UsersDAO {

    public static void registerUser(User user) {
        if (userExists(user.getUsername())) {
            System.out.println("El usuario ya existe");
            throw new IllegalArgumentException("El usuario ya existe");
        }
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement("INSERT INTO Usuario (nombre, contrasenia, apellido, dni, telefono, idTipoUsuario) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getDNI());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setInt(6, user.getUserTypeId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Usuario registrado correctamente");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static User getUser(String username) {
        String query = "SELECT * FROM Usuario WHERE nombre = ?";
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("nombre"));
                user.setPassword(rs.getString("contrasenia"));
                user.setSurname(rs.getString("apellido"));
                user.setDNI(rs.getString("dni"));
                user.setPhone(rs.getString("telefono"));
                user.setUserTypeId(rs.getInt("idTipoUsuario"));
                if (user.getUserTypeId() == 1) {
                    user.setAdmin(true);
                }
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static boolean userExists(String username) {
        String query = "SELECT COUNT(*) FROM Usuario WHERE nombre = ?";
        try {
            Connection conn = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}