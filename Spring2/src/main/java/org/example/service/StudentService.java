package ru.skillbox.students_registration.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.skillbox.students_registration.event.CreateStudentEvent;
import ru.skillbox.students_registration.event.DeleteStudentEvent;
import ru.skillbox.students_registration.model.Student;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    @Value("${app.create-mock-data:false}")
    private boolean createMockData;

    private final ApplicationEventPublisher eventPublisher;
    private final Map<Integer, Student> students = new HashMap<>();

    public Student getStudent(int id) {
        return students.get(id);
    }

    public Map<Integer, Student> getAllStudents() {
        return students;
    }

    public void clearStudents() {
        students.clear();
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
        eventPublisher.publishEvent(new CreateStudentEvent(this, student));
    }

    public void deleteStudent(int id) {
        students.remove(id);
        eventPublisher.publishEvent(new DeleteStudentEvent(this, id));
    }

    @PostConstruct
    public void init() {
        if (createMockData) {
            log.info("Creating mock data");

            addStudent(new Student("Ivan", "Ivanov", 33));
            addStudent(new Student("Pavel", "Bakanov", 40));
            addStudent(new Student("Mariia", "Semina", 25));
        }
    }
}