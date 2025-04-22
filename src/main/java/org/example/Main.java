package org.example;

import org.example.utilities.InputUtils;

public class Main {
    public static void main(String[] args) {
        // Проверка
        System.out.println(InputUtils.inputString("Введите текст:"));
        System.out.println(InputUtils.inputInt("Введите целое число:"));
        System.out.println(InputUtils.inputDouble("Введите число:"));
        System.out.println(InputUtils.inputMenuChoice(1,5, "Выберите действие от 1 до 5: "));
    }
}