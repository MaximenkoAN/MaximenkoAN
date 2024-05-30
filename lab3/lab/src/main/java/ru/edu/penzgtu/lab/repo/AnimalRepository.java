package ru.edu.penzgtu.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.lab.entity.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal findByName(String name);

    List<Animal> findAnimalByName(String name);
}
