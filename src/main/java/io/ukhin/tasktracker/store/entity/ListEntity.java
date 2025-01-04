package io.ukhin.tasktracker.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "list")
public class ListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToMany
    @JoinTable(name = "client_list",
            joinColumns = @JoinColumn(name = "list_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    List<ClientEntity> clients = new ArrayList<>();

    @OneToMany(mappedBy = "list")
    List<TaskEntity> tasks = new ArrayList<>();
}
