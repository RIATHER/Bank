package org.example.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class NumberUtils {
    private static final Logger logger = LoggerFactory.getLogger(NumberUtils.class);
    public static long GeneratedRandomNumber(long max){
        try {
            Random random = new Random();
            long bound = max - 1;
            long value = Math.abs(random.nextLong()) % bound;
            return 1 + value;
        } catch (Exception e) {
            logger.error("Ошибка в генерации числа");
        }
        return -1;
    }
}
