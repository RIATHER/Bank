package org.example.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class NumberUtils {
    // Логгер для математических утилит? Ну ебать, оформляем тогда
    private static final Logger logger = LoggerFactory.getLogger(NumberUtils.class);
    // Этот конченый метод отвечает за генерацию случайного числа, которое деградирует с каждой генерацией, а
    // всё потому, что без этого числа, ты вообще хуй поймёшь, как создать номер аккаунта.
    public static long GeneratedRandomNumber(long min, long max){
        // А ты попробуй сгенерировать число меньше минимального или больше максимального,
        // да хер у тебя это получится. Хотя... Только попробуй сука...
        if (max <= min) {
            logger.error("Максимум должен быть больше минимума: min={}, max={}", min, max);
            throw new IllegalArgumentException("Максимум должен быть больше минимума");
        }
        // Прошёл проверку на адекватность? Ну таки продолжаем нахуй, не адекват ты чёртов!
        long number = ThreadLocalRandom.current().nextLong(min, max);
        // Выводим это всё в логи, потому-что я хочу, а тебя ебать это не должно!
        logger.info("Случайное число получено: {}", number);
        // Он вернулся на родину мать его!!!
        return number;
    }
}
