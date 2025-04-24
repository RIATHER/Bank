package org.example.utilities.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager{
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static final Properties prop = new Properties();
    static {
        try {
            InputStream config = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties");
            if (config == null){
                logger.error("Файл конфигурации не найден");
            } else {
                logger.info("Файл конфигурации успешно загружен");
                prop.load(config);
            }
        } catch (IOException e) {
            logger.error("Ошибка при загрузке конфигурации");
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("ConstantConditions") // Подавление Warn(config == null)
    private static String getProp(String key, boolean requeried){
        String config = prop.getProperty(key);
        if  (requeried && (config.trim().isEmpty() || config == null)){
            logger.error("Значение по ключу {} не найдено", key);
            throw new IllegalArgumentException("Ключ " + key + "обязателен");
        }
        return config;
    }
    // Получение скрытых данных
    public static String getDatabaseUrl(){
        return getProp("db.url", true);
    }
    public static String getUserUsername(){
        return getProp("db.username", true);
    }
    public static String getUserPassword(){
        return getProp("db.password", true);
    }
    public static String getDataBaseAppVersion(){
        return getProp("db.version", false);
    }
    public static String getDataBaseAppName(){
        return getProp("db.name", false);
    }
}