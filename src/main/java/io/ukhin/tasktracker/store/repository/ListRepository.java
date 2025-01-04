package io.ukhin.tasktracker.store.repository;

import io.ukhin.tasktracker.store.entity.ClientEntity;
import io.ukhin.tasktracker.store.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListRepository extends JpaRepository<ListEntity, Long> {
    List<ListEntity> findListEntitiesByClients_Id(Long clientsId);
}
