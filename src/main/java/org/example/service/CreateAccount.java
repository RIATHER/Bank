package org.example.service;

import org.example.utilities.DBUtils;
import org.example.utilities.InputUtils;
import org.example.utilities.NumberUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateAccount {
    private static final Logger logger = LoggerFactory.getLogger(CreateAccount.class);
    private static final String SqlInsert = """
            INSERT INTO credentials
            (account_id, username, password_hash)
            VALUES (?, ?, ?)
            """;
    // Генерация номера аккаунта
    private static String generateNumberAccount() {
        long randomNumber = NumberUtils.GeneratedRandomNumber(1_000_000_000_000_000L);
        if (randomNumber != -1) {
            return "ACC" + randomNumber;
        }
        return null;
    }
    // Ввод имени пользователя
    private static String inputNameOwner() {
        String OwnerName = InputUtils.inputString("Введите имя пользователя:");
        OwnerName = OwnerName.trim();
        if (OwnerName.isEmpty()) {
            System.out.println("Имя не может быть пустым!");
        } else if (OwnerName.matches(".*\\d+.*")) {
            System.out.println("Имя не может содержать цифры!");
        } else if (OwnerName.matches("\".*[^a-zA-Zа-яА-ЯёЁ].*\"")) {
            System.out.println("Имя не может содержать специальные символы или пробелы!");
        } else {
            return OwnerName;
        }
        return null;
    }
    // Ввод пароля пользователя
    private static String inputPassword() {
        String Password = InputUtils.inputString("Введите новый пароль:");
        Password = Password.trim();
        if (Password.isEmpty()) {
            System.out.println("Имя не может быть пустым!");
        } else if (Password.length() < 8) {
            System.out.println("Слишком маленький пароль!");
        } else {
            return Password;
        }
        return null;
    }
    // Создание аккаунта
    public static void createAccount() {
        try (Connection connect = DBUtils.getConnection();
            PreparedStatement statement = connect.prepareStatement(SqlInsert)) {
            statement.setString(1, generateNumberAccount());
            statement.setString(2, inputNameOwner());
            statement.setString(3, inputPassword());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Аккаунт успешно добавлен!");
            }
        } catch (SQLException e) {
            logger.error("Ошибка в запросе базы данных");
        }
    }
}