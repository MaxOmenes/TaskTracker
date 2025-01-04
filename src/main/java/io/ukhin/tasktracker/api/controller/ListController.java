package io.ukhin.tasktracker.api.controller;

import io.ukhin.tasktracker.api.dto.ListDto;
import io.ukhin.tasktracker.api.dto.TaskDto;
import io.ukhin.tasktracker.api.exception.ClientNotFoundException;
import io.ukhin.tasktracker.api.factory.ListDtoFactory;
import io.ukhin.tasktracker.store.entity.ClientEntity;
import io.ukhin.tasktracker.store.entity.ListEntity;
import io.ukhin.tasktracker.store.repository.ClientRepository;
import io.ukhin.tasktracker.store.repository.ListRepository;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ListController {
    ListRepository listRepository;
    ListDtoFactory listDtoFactory;
    ClientRepository clientRepository;



    @PostMapping("/create")
    public ListDto createList(@RequestParam String name, @RequestParam String creator) {
        //need to change this to id when frontend is ready
        ClientEntity client = clientRepository.findByUsername(creator).or(() -> {
            throw new ClientNotFoundException("Client not found");
        }).get();
        ListEntity list = listRepository.save(ListEntity.builder()
                .name(name)
                .clients(List.of(client))
                .build());
        return listDtoFactory.createListDto(list);
    }

    @GetMapping("/tasks")
    public List<TaskDto> getLists(@RequestParam String list_id) {
        return listRepository.findById(Long.parseLong(list_id)).or(() -> {
            throw new RuntimeException("List not found");
        }).get().getTasks().stream().map(task -> TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .status(task.getStatus().name())
                .build()).toList();
    }


}
