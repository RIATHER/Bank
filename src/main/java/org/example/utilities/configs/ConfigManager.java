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
                logger.warn("Файл конфигурации не найден");
            } else {
                logger.info("Файл конфигурации успешно загружен");
                prop.load(config);
            }
        } catch (IOException e) {
            logger.error("Ошибка при загрузке конфигурации");
            throw new RuntimeException(e);
        }
    }
    private static String getProp(String key){
        return prop.getProperty(key);
    }
    // Получение скрытых данных
    public static String getDatabaseUrl(){
        return getProp("db.url");
    }
    public static String getUserUsername(){
        return getProp("db.username");
    }
    public static String getUserPassword(){
        return getProp("db.password");
    }
}