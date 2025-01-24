package effective.mobile.tracker.service;

import effective.mobile.tracker.dto.CreateTaskDto;
import effective.mobile.tracker.exceptions.NotFoundByLoginException;
import effective.mobile.tracker.mapper.TaskMapper;
import effective.mobile.tracker.model.User;
import effective.mobile.tracker.model.task.Task;
import effective.mobile.tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    public Long create(CreateTaskDto createTaskDto){
        User user = this.userService.findByEmail(createTaskDto.getUserEmail())
                .orElseThrow(() -> new NotFoundByLoginException(User.class, createTaskDto.getUserEmail()));

        Task task = taskMapper.toEntity(createTaskDto);
        task.setUser(user);
        this.taskRepository.save(task);
        return task.getId();
//        return null;
    }
}
