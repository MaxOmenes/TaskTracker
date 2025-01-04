package io.ukhin.tasktracker.api.controller;

import io.ukhin.tasktracker.api.dto.TaskDto;
import io.ukhin.tasktracker.api.factory.TaskDtoFactory;
import io.ukhin.tasktracker.store.entity.ListEntity;
import io.ukhin.tasktracker.store.entity.TaskEntity;
import io.ukhin.tasktracker.store.repository.ListRepository;
import io.ukhin.tasktracker.store.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TaskController {
    TaskRepository taskRepository;
    TaskDtoFactory taskDtoFactory;
    ListRepository listRepository;

    @PostMapping("/create")
    public TaskDto createTask(@RequestParam String name, @RequestParam String list_id) {
        ListEntity list = listRepository.findById(Long.parseLong(list_id)).or(() -> {
            throw new RuntimeException("List not found");
        }).get();

        TaskEntity task = taskRepository.save(TaskEntity.builder()
                .name(name)
                .status(TaskEntity.TaskStatus.UNCHECKED)
                .list(list)
                .build());

        return taskDtoFactory.createTaskDto(task);
    }

    @GetMapping("/get")
    public Optional<TaskDto> getTask(@RequestParam String task_id) {
        return taskRepository.findById(Long.parseLong(task_id)).map(taskDtoFactory::createTaskDto);
    }

    @PutMapping("/update")
    public void updateTask(@RequestParam String task_id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String status) {
        taskRepository.findById(Long.parseLong(task_id)).or(() -> {
            throw new RuntimeException("Task not found");
        }).ifPresent(task -> {
            if (name != null) {
                task.setName(name);
            }
            if (status != null) {
                task.setStatus(TaskEntity.TaskStatus.valueOf(status));
            }
            taskRepository.save(task);
        });
    }

    @DeleteMapping("/delete")
    public String deleteTask(@RequestParam String task_id) {
        taskRepository.deleteById(Long.parseLong(task_id));
        return String.format("Task %s deleted", task_id);
    }
}
