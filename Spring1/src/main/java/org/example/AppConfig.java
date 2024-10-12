package org.example;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    @Profile("init")
    public ContactsRepository initializeWithDefaultContacts() {
        ContactsRepository repository = new ContactsRepository();
        repository.loadFromFile("/src/main/resources/default-contacts.txt");
        return repository;
    }
}