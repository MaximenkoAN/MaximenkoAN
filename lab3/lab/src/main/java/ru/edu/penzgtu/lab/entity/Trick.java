package ru.edu.penzgtu.lab.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tricks")
public class Trick {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false,length = 52)
    private String name;

    @Column(name = "description",nullable = false,length = 52)
    private String description;

    @Column(name = "category",nullable = false,length = 52)
    private String category;

    @Column(name = "difficulty",nullable = false,length = 52)
    private Long difficulty;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;
}
