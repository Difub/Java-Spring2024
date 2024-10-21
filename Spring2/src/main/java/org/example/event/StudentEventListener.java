package org.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentEventListener {

    @EventListener
    public void onStudentCreated(CreateStudentEvent event) {
        log.info("Created student: " + event.getStudent().toString());
    }

    @EventListener
    public void onStudentDeleted(DeleteStudentEvent event) {
        log.info("Deleted student with ID: " + event.getId());
    }
}
