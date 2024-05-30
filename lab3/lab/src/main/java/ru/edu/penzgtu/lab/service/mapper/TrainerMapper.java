package ru.edu.penzgtu.lab.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.TrainerDto;
import ru.edu.penzgtu.lab.entity.Trainer;
import ru.edu.penzgtu.lab.repo.TrickRepository;
import ru.edu.penzgtu.lab.entity.Trick;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrainerMapper {
    private final TrickRepository trickRepository;


    public List<TrainerDto> toListDto(List<Trainer> trainers) {
        return trainers.stream().map(this::toDto).toList();
    }

    public TrainerDto toDto(Trainer trainer) {
        return TrainerDto.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .country(trainer.getCountry())
                .yearsOld(trainer.getYearsOld())
                .description(trainer.getDescription())
                .localDateTime(trainer.getLocalDateTime())
                .tricks(trainer.getTricks().stream()
                        .map(Trick::getName)
                        .toList())
                .build();
    }

    public Trainer toEntity(TrainerDto trainerDto) {
        Trainer trainer = new Trainer();

        trainer.setId(trainerDto.getId());
        trainer.setName(trainerDto.getName());
        trainer.setCountry(trainerDto.getCountry());
        trainer.setYearsOld(trainerDto.getYearsOld());
        trainer.setDescription(trainerDto.getDescription());
        trainer.setLocalDateTime(trainerDto.getLocalDateTime());
        trainer.setTricks((Collections.singletonList
                (trickRepository.findByName(String.valueOf(trainerDto.getTricks().stream().toList(
                ))))));

        return trainer;
    }
}
