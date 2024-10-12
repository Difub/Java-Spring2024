package org.example;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactsRepository contactsRepository;

    public void addContact(Contact contact) {
        contactsRepository.add(contact);
    }

    public Optional<Contact> findByEmail(String email) {
        return contactsRepository.findByEmail(email);
    }

    public boolean deleteContact(String email) {
        return contactsRepository.delete(email);
    }

    public List<Contact> getAllContacts() {
        return contactsRepository.getAll();
    }

    public void saveToFile(String filePath) {
        contactsRepository.saveToFile(filePath);
    }

    public void loadFromFile(String filePath) {
        contactsRepository.loadFromFile(filePath);
    }
}
