package ru.edu.penzgtu.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.lab.entity.Trick;

import java.util.List;

@Repository
public interface TrickRepository extends JpaRepository<Trick, Long> {
    Trick findByName(String name);

    List<Trick> findTrickByName(String name);
}
