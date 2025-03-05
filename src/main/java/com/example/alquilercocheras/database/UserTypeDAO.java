package com.example.alquilercocheras.database;

import com.example.alquilercocheras.models.UserType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserTypeDAO {

    public static List<UserType> getUserTypes(){

        Connection conn = DatabaseConnection.getInstance();
        if (conn == null) {
            System.out.println("No se pudo obtener la conexión a la base de datos.");
            return null;
        }
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM TipoUsuario");
            List<UserType> userTypes = new ArrayList<>();
            while (rs.next()) {
                UserType userType = new UserType();
                userType.setIdUserType(rs.getInt("idTipoUsuario"));
                userType.setDescription(rs.getString("descripcion"));
                userTypes.add(userType);
            }

            return userTypes;

        } catch (SQLException e) {
            System.out.println("Error al verificar si la base de datos tiene usuarios: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

}
