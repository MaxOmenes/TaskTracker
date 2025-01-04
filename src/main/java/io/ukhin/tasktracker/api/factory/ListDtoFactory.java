package io.ukhin.tasktracker.api.factory;

import io.ukhin.tasktracker.api.dto.ListDto;
import io.ukhin.tasktracker.store.entity.ListEntity;
import org.springframework.stereotype.Component;

@Component
public class ListDtoFactory {
    public ListDto createListDto(ListEntity listEntity) {
    return ListDto.builder()
            .id(listEntity.getId())
            .name(listEntity.getName())
            .build();
}
}
