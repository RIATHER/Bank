package org.example.utilities;

import org.example.utilities.configs.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils{
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                ConfigManager.getDatabaseUrl(),
                ConfigManager.getUserUsername(),
                ConfigManager.getUserPassword());
    }
}
