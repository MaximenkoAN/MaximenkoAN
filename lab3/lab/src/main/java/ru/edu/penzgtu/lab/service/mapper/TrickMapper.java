package ru.edu.penzgtu.lab.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.TrickDto;
import ru.edu.penzgtu.lab.entity.Trick;
import ru.edu.penzgtu.lab.repo.TrainerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrickMapper {
    private final TrainerRepository trainerRepository;

    public List<TrickDto> toListDto(List<Trick> tricks) {
        return tricks.stream().map(this::toDto).toList();
    }

    public TrickDto toDto(Trick trick) {
        String trainerName = trick.getTrainer() != null ?
                trick.getTrainer().getName() : null;
        return TrickDto.builder()
                .id(trick.getId())
                .name(trick.getName())
                .description(trick.getDescription())
                .category(trick.getCategory())
                .difficulty(trick.getDifficulty())
                .localDateTime(trick.getLocalDateTime())
                .trainerName(trainerName)
                .build();
    }

    public Trick toEntity(TrickDto trickDto) {
        Trick trick = new Trick();

        trick.setId(trickDto.getId());
        trick.setName(trickDto.getName());
        trick.setDescription(trickDto.getDescription());
        trick.setCategory(trickDto.getCategory());
        trick.setDifficulty(trickDto.getDifficulty());
        trick.setTrainer(trainerRepository.findByName(trickDto.getTrainerName()));

        return trick;
    }
}
