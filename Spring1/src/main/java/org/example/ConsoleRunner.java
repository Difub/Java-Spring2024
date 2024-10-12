package org.example;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private ContactService contactService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Ваш выбор: ");

            switch (choice) {
                case 1:
                    listContacts();
                    break;
                case 2:
                    addContact(scanner);
                    break;
                case 3:
                    deleteContact(scanner);
                    break;
                case 4:
                    saveContacts();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Неправильный выбор.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("Меню:");
        System.out.println("1. Просмотреть все контакты");
        System.out.println("2. Добавить контакт");
        System.out.println("3. Удалить контакт");
        System.out.println("4. Сохранить контакты в файл");
        System.out.println("5. Выход");
    }

    private void listContacts() {
        contactService.getAllContacts().forEach(System.out::println);
    }

    private void addContact(Scanner scanner) {
        System.out.print("Введите ФИО; номер телефона; email: ");
        String input = scanner.nextLine();
        String[] parts = input.split(";");
        if (parts.length == 3) {
            Contact contact = new Contact(parts[0].trim(), parts[1].trim(), parts[2].trim());
            contactService.addContact(contact);
            System.out.println("Контакт успешно добавлен.");
        } else {
            System.out.println("Некорректный ввод. Попробуйте снова.");
        }
    }

    private void deleteContact(Scanner scanner) {
        System.out.print("Введите email для удаления: ");
        String email = scanner.nextLine();
        if (contactService.deleteContact(email)) {
            System.out.println("Контакт удален.");
        } else {
            System.out.println("Контакт не найден.");
        }
    }

    private void saveContacts() {
        contactService.saveToFile("contacts.txt");
        System.out.println("Контакты сохранены в файл.");
    }

    private int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите целое число.");
            }
        }
    }
}