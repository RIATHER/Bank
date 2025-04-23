package org.example.service;

import org.example.console.PrintMenu;
import org.example.utilities.InputUtils;

public class Authorization{
    public static void start(){
        while(true){
            System.out.print(PrintMenu.PrintMenuAuthorization());
            int choice = InputUtils.inputMenuChoice(0,2,"Введите действие:");
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    System.out.println("Пока не реализовано :2");
                    break;
                case 2:
                    CreateAccount.createAccount();
                    break;
            }
        }
    }
}