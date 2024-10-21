package org.example.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.skillbox.students_registration.model.Student;

@Getter
public class CreateStudentEvent extends ApplicationEvent {
    private final Student student;

    public CreateStudentEvent(Object source, Student student) {
        super(source);
        this.student = student;
    }
}