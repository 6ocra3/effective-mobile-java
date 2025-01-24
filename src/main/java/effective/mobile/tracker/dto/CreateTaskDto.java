package effective.mobile.tracker.dto;

import effective.mobile.tracker.model.task.TaskPriority;
import effective.mobile.tracker.model.task.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskDto {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private String userEmail;
}
