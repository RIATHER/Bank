package org.example.service;

import org.example.utilities.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountValidation{
    // Хорошая штука этот ваш ёбанный логгер, потому что без него мы не узнаем, что и когда пойдёт через жопу
    private static final Logger logger = LoggerFactory.getLogger(AccountValidation.class);
    // Этот метод проверяет уникальность номера аккаунта в базе. Если не уникально, оно тебе сразу в лицо плюнет
    public static boolean isAccountNumberUnique(String accountNumber) {
        // Это ебучий SQL-запрос — он сам не решит твою жизнь, но даже если ты тупой, то мы его всё равно заебашим
        String sqlQuery = """
            SELECT account_id FROM credentials
            WHERE account_id = ?
            """;
        // Итак, проверяем в базе — занят ли номер, ну ты сам должен понимать, не маленький уже
        try (Connection connect = DBUtils.getConnection(); // Подключаемся, потому-что без БД, Мы УмРём
             PreparedStatement statement = connect.prepareStatement(sqlQuery)){
            // Передаем номер аккаунта — если кто-то вбил говнище, это виновата тупость генератора чисел, а не моя
            statement.setString(1, accountNumber);
            // Выполняем запрос. Если тебе база вернула номер, значит ты идёшь нахуй,
            // а если не идёшь, то в любом случае пойдешь, ему похуй
            try (ResultSet resultSet = statement.executeQuery()){
                // А так хотелось чтобы другой съебался с этого номера. Сорян бро, так нельзя
                return !resultSet.next();
            }
        } catch (SQLException e){
            // База сломалась? Ещё бы! Лови эту злоебучую ошибку, что-бы ты понял, что БД на тебя похуй
            logger.error("Ошибка при проверке уникальности номера аккаунта");
        }
        // Ну тут нечего сказать... Если всё идёт по сценарию "я пойду другим путём",
        // то значит ты нихуя не уникален, пошёл нахуй отсюда
        return false;
    }
    // Валидируем введенное имя, потому что если оно дерьмо, то ты блять об этом точно узнаешь!
    public static boolean isNameValidate(String name){
        // Проверка на пустое имя или на значение уебанства, состоящее из одних конченных пробелов. ТЫ НЕ ПРОЙДЁШЬ!
        if (name == null || name.isEmpty()) {
            System.out.println("Имя не может быть пустым или состоять только из пробелов!");
            return false;
        }
        // Проверка если имя содержит цифры, ты чё, ебать? Это не номер счета, а человеческое имя,
        // а если ты не человек, то нахуй ты сюда вообще пришёл?
        if (name.matches(".*\\d+.*")) {
            System.out.println("Имя не может содержать цифры!");
            return false;
        }
        // Специальные символы в имени? Ты чо блять, долбоёб?
        // Это не место для всяких специальных символов, ты чё нахуй пишешь вообще?
        if (name.matches(".*[^a-zA-Zа-яА-ЯёЁ\\s].*")) {
            System.out.println("Имя не может содержать специальные символы или цифры!");
            return false;
        }
        // Если имя слишком короткое — да ты сука внатуре дурачок, зачем тебе имя, которое выглядит как хренопень?
        if (name.length() < 3){
            System.out.println("Имя не может быть коротким!");
            return false;
        }
        // Если всё нормально, то я в тебя всё-равно не верю, щас наебашишь мне в пароле всякой хуйни,
        // потом мне сука сидеть плакать: "ЫЫЫ, КАК ТЫ ЭТО СДЕЛАЛ?"
        return true;
    }
}