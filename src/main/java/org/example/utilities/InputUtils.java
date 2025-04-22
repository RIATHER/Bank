package org.example.utilities;

import java.util.Scanner;

public class InputUtils {
    // Сканнер
    private static final Scanner scanner = new Scanner(System.in);
    // Ввод строки
    public static String inputString(String Message){
        System.out.print(Message + " ");
        return scanner.nextLine().trim();
    }
    // Ввод целого числа
    public static int inputInt(String Message){
        while (true){
            System.out.print(Message + " ");
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                System.out.println("Ошибка: Введите целое число!");
            }
        }
    }
    // Ввод числа с плавающей точкой
    public static double inputDouble(String Message){
        while (true){
            System.out.print(Message + " ");
            String value = scanner.nextLine().trim();
            try {
                return Double.parseDouble(value);
            } catch (Exception e) {
                System.out.println("Ошибка: Введите число!");
            }
        }
    }
    // Выбор действия в меню
    public static int inputMenuChoice(int min, int max, String Message){
        while (true){
            System.out.print(Message + " ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                // Проверка
                if (min > choice || max < choice){
                    System.out.println("Ошибка: Такого действия не существует!");
                } else {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Выберите действие!");
            }
        }
    }
}