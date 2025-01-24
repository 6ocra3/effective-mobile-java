package effective.mobile.tracker.controller;

import effective.mobile.tracker.dto.CreateTaskDto;
import effective.mobile.tracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public Long create(@RequestBody CreateTaskDto createTaskDto){
        return taskService.create(createTaskDto);
    }
}
