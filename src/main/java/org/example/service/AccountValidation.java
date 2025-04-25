package org.example.service;

import org.example.utilities.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountValidation{
    private static final Logger logger = LoggerFactory.getLogger(AccountValidation.class);
    public static boolean isAccountNumberUnique(String accountNumber) {
        String sqlQuery = """
            SELECT account_id FROM credentials
            WHERE account_id = ?
            """;
        try (Connection connect = DBUtils.getConnection();
             PreparedStatement statement = connect.prepareStatement(sqlQuery)){
            statement.setString(1, accountNumber);
            try (ResultSet resultSet = statement.executeQuery()){
                return !resultSet.next();
            }
        } catch (SQLException e){
            logger.error("Ошибка при проверке уникальности номера аккаунта");
        }
        return false;
    }
    public static boolean isNameValidate(String name){
        if (name == null || name.isEmpty()) {
            System.out.println("Имя не может быть пустым или состоять только из пробелов!");
            return false;
        }
        if (name.matches(".*\\d+.*")) {
            System.out.println("Имя не может содержать цифры!");
            return false;
        }
        if (name.matches(".*[^a-zA-Zа-яА-ЯёЁ\\s].*")) {
            System.out.println("Имя не может содержать специальные символы или цифры!");
            return false;
        }
        if (name.length() < 3){
            System.out.println("Имя не может быть коротким!");
            return false;
        }
        return true;
    }
    public static boolean isValidatePassword(String password){
        if (password == null || password.isEmpty()){
            System.out.println("Пароль не может быть пустым");
            return false;
        }
        if (password.length() < 8) {
            System.out.println("Пароль не может быть меньше 8 символов");
            return false;
        }
        if (password.matches(".*\\s+.*")){
            System.out.println("В пароле не могут быть пробелы!");
            return false;
        }
        if (!password.matches(".*[A-Z].*")){
            System.out.println("Пароль должен содержать хотя бы одну заглавную букву");
            return false;
        } else if (!password.matches(".*[a-z].*")){
            System.out.println("Пароль должен содержать хотя бы одну строчную букву");
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            System.out.println("Пароль должен содержать хотя бы одну цифру");
            return false;
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            System.out.println("Пароль должен содержать хотя бы один специальный символ");
            return false;
        }
        return true;
    }
}