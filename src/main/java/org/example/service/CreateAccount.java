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
    private static final String sqlAddAccount = """
            INSERT INTO credentials
            (account_id, username, password_hash)
            VALUES (?, ?, ?)
            """;
    // это готово ни трогать ничого
    private static String generateNumberAccount() {
        while(true){
            long randomNumber = NumberUtils.GeneratedRandomNumber(1_000_000_000_000_000L,
                                                                  10_000_000_000_000_000L);
            if(randomNumber == -1){
                logger.warn("Ошибка при генерации номера аккаунта");
                continue;
            }
            String account_id = "ACC" + randomNumber;
            if(AccountValidation.isAccountNumberUnique(account_id)){
                logger.info("Уникальный номер аккаунта сгенерирован: {}", account_id);
                return account_id;
            } else {
                logger.warn("Данный номер аккаунта уже существует");
            }
        }
    }
    // Ввод имени пользователя
    private static String inputNameOwner() {
        while(true){
            String OwnerName = InputUtils.inputString("Введите имя пользователя:");
            OwnerName = OwnerName.trim();
            if (!AccountValidation.isNameValidate(OwnerName)){
                logger.warn("Имя не подходит под валидацию");
            } else {
                logger.info("Успешно введено имя аккаунта");
                return OwnerName;
            }
        }
    }
    // Ввод пароля пользователя
    private static String inputPassword() {
        String Password = InputUtils.inputString("Введите новый пароль:");
        Password = Password.trim();
        if (Password.isEmpty()) {
            System.out.println("Пароль не может быть пустым!");
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
            PreparedStatement statement = connect.prepareStatement(sqlAddAccount)) {
            // Проверка на подключение к БД с логированием
            if (connect.isValid(5)) {
                logger.info("Успешное подключение к базе данных");
            } else {
                logger.warn("Подключение установлено, но невалидное");
            }
            statement.setString(1, generateNumberAccount());
            statement.setString(2, inputNameOwner());
            statement.setString(3, inputPassword());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("Аккаунт добавлен в баазу данных");
                System.out.println("Аккаунт успешно создан!");
            }
        } catch (SQLException e) {
            logger.error("Ошибка в запросе базы данных", e);
        }
    }
}