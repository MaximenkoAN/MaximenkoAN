package ru.edu.penzgtu.lab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "habitats")
public class Habitat {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false,length = 52)
    private String name;

    @Column(name = "description",nullable = false,length = 52)
    private String description;

    @Column(name = "climate",nullable = false,length = 52)
    private String climate;

    @Column(name = "area",nullable = false,length = 52)
    private Long area;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "habitat", fetch = FetchType.LAZY, cascade =
            {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private List<Animal> animals = new ArrayList<>();
}
