package com.example.alquilercocheras.database;

import com.example.alquilercocheras.models.User;

import java.sql.PreparedStatement;

public class UsersDAO {

/*

    public static void addNewUser(User user){
        try {
            Connection connection = Database.getConnection();
            String query = "INSERT INTO users (nombre, password,idTipoUsuario) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserTypeId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName());

    }
}

*/
}