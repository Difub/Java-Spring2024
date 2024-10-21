package org.example.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DeleteStudentEvent extends ApplicationEvent {
    private final int id;

    public DeleteStudentEvent(Object source, int id) {
        super(source);
        this.id= id;
    }
}