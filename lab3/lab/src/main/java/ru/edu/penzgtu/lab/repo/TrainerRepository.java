package ru.edu.penzgtu.lab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.penzgtu.lab.entity.Trainer;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {

    Trainer findByName(String name);

    List<Trainer> findTrainerByName(String name);
}
