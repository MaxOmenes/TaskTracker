package io.ukhin.tasktracker.api.factory;

import io.ukhin.tasktracker.api.dto.TaskDto;
import io.ukhin.tasktracker.store.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {
    public TaskDto createTaskDto(TaskEntity taskEntity) {
        return TaskDto.builder()
                .id(taskEntity.getId())
                .name(taskEntity.getName())
                .status(taskEntity.getStatus().name())
                .build();
    }
}
