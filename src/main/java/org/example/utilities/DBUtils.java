package org.example.utilities;

import org.example.utilities.configs.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils{
    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(
                   ConfigManager.getDatabaseUrl(),
                   ConfigManager.getUserUsername(),
                   ConfigManager.getUserPassword());
        } catch (SQLException e) {
            logger.error("Ошибка подключения к базе данных: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Ошибка при подключении к базе данных: {}", e.getMessage());
            throw new RuntimeException("Не удалось подключиться к базе данных", e);
        }
    }
}