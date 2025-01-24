package effective.mobile.tracker.mapper;

import effective.mobile.tracker.dto.CreateTaskDto;
import effective.mobile.tracker.model.task.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    @Mapping(target = "user", ignore = true)
    Task toEntity(CreateTaskDto createTaskDto);

}
