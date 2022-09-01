package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO implements InterfaceDAO{
    private final String URL = "jdbc:mysql://localhost:3306/provider";
    private final String USER = "root";
    private final String PASSWORD = "RomanKotyk999";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
