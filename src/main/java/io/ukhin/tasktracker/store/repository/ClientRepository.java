package io.ukhin.tasktracker.store.repository;

import io.ukhin.tasktracker.store.entity.ClientEntity;
import io.ukhin.tasktracker.store.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByUsername(String username);
}
