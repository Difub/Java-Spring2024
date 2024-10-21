package org.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.skillbox.students_registration.model.Student;


@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final StudentService studentService;

    @ShellMethod(key = "add")
    public String addStudent(@ShellOption(value = "--n",defaultValue = "Ivan") String firstName,
                             @ShellOption(value = "--l",defaultValue = "Ivanov") String lastName,
                             @ShellOption(value = "--a",defaultValue = "25") int age) {
        Student student = new Student(firstName, lastName, age);
        studentService.addStudent(student);
        return "Added student: " + student;
    }

    @ShellMethod(key = "del")
    public String deleteStudent(int id) {
        studentService.deleteStudent(id);
        return "Deleted student with ID: " + id;
    }

    @ShellMethod(key = "all")
    public void getStudents() {
        for (Student student : studentService.getAllStudents().values()) {
            System.out.println(student.toString());
        }
    }

    @ShellMethod(key = "clearall")
    public String clearStudents() {
        studentService.clearStudents();
        return "All students have been cleared.";
    }
}