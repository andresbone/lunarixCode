package com.farmacia.prototipo.config;

/*
 * @author andres bone
 */
import java.sql.Connection;    
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBD {

    private static final String URL = "jdbc:mysql://localhost:3306/farmacia_inventario";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connectBD() {

        Connection connect = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connect = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(">>> ¡Conexion exitosa a la base de datos! <<<");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el Driver.");
        } catch (SQLException e) {
            System.err.print("Error de SQL: " + e.getMessage());
        }
        return connect;
    }

}
