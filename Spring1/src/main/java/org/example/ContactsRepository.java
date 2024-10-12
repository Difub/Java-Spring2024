package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!init")
public class ContactsRepository {

    private final List<Contact> contacts = new ArrayList<>();

    @Value("${app.contact.file}")
    private String filePath;

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public Optional<Contact> findByEmail(String email) {
        return contacts.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public boolean delete(String email) {
        return contacts.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }

    public List<Contact> getAll() {
        return contacts;
    }

    public void saveToFile(String filePath) {
        try {
            Files.writeString(
                    Paths.get(filePath),
                    contacts.stream()
                            .map(this::contactToString)
                            .collect(Collectors.joining("\n"))
            );
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public void loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                } else {
                    System.out.println("Неверный формат строки: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private String contactToString(Contact contact) {
        return String.format("%s;%s;%s", contact.getFullName(), contact.getPhoneNumber(), contact.getEmail());
    }
}