package org.example;

import lombok.Data;

//Сущность «Студент» должна представлять из себя идентификатор (id), имя (firstName), фамилию (lastName) и возраст (age).
@Data
public class Student {
    private static int idCounter = 0;
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public Student(String firstName, String lastName, int age) {
        this.id = ++idCounter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}