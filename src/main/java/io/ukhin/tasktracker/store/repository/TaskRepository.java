package io.ukhin.tasktracker.store.repository;

import io.ukhin.tasktracker.store.entity.ListEntity;
import io.ukhin.tasktracker.store.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findAllByList(ListEntity list);
}
