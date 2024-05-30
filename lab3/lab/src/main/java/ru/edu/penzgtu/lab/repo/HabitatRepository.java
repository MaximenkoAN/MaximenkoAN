package ru.edu.penzgtu.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.lab.entity.Habitat;

import java.util.List;

@Repository
public interface HabitatRepository extends JpaRepository<Habitat, Long> {
    Habitat findByName(String name);

    List<Habitat> findHabitatByName(String name);
}