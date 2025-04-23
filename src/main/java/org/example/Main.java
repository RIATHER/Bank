package org.example;

import org.example.service.Authorization;
import org.example.utilities.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        // Проверка на подключение к базе данных
        try(Connection conn = DBUtils.getConnection()){
            if (conn != null && !conn.isClosed()){
                System.out.println("Успешное подключение к базе данных");
            }
        } catch (SQLException e){
            System.err.println("Ошибка при подключении к базе данных" + e.getMessage());
        }
        Authorization.start();
    }
}