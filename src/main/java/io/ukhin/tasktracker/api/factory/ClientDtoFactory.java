package io.ukhin.tasktracker.api.factory;

import io.ukhin.tasktracker.api.dto.ClientDto;
import io.ukhin.tasktracker.store.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoFactory {
    public ClientDto createClientDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .id(clientEntity.getId())
                .username(clientEntity.getUsername())
                .password(clientEntity.getPassword())
                .build();
    }
}
