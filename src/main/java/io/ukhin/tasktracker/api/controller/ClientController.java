package io.ukhin.tasktracker.api.controller;

import io.ukhin.tasktracker.api.dto.ClientDto;
import io.ukhin.tasktracker.api.dto.ListDto;
import io.ukhin.tasktracker.api.exception.ClientNotFoundException;
import io.ukhin.tasktracker.api.factory.ClientDtoFactory;
import io.ukhin.tasktracker.api.factory.ListDtoFactory;
import io.ukhin.tasktracker.store.entity.ClientEntity;
import io.ukhin.tasktracker.store.entity.ListEntity;
import io.ukhin.tasktracker.store.repository.ClientRepository;
import io.ukhin.tasktracker.store.repository.ListRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ClientController {
    ClientRepository clientRepository;
    ClientDtoFactory clientDtoFactory;
    ListRepository listRepository;
    ListDtoFactory listDtoFactory;

    @PostMapping("/register")
    public ClientDto register(@RequestParam String username, @RequestParam String password) {
        ClientEntity client = clientRepository.save(ClientEntity.builder()
                .username(username)
                .password(password)
                .build());
        return clientDtoFactory.createClientDto(client);
    }

    @GetMapping("/lists")
    public List<ListDto> getLists(@RequestParam String client_id) {
        return listRepository.findListEntitiesByClients_Id(
                clientRepository.findById(Long.parseLong(client_id)).or(
                        () -> {throw new ClientNotFoundException("Client Not found");}
                ).get().getId()).stream().map(listDtoFactory::createListDto).toList();
    }
}
