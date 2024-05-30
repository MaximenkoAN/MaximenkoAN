package ru.edu.penzgtu.lab.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.HabitatDto;
import ru.edu.penzgtu.lab.entity.Animal;
import ru.edu.penzgtu.lab.entity.Habitat;
import ru.edu.penzgtu.lab.entity.Trick;
import ru.edu.penzgtu.lab.repo.AnimalRepository;
import ru.edu.penzgtu.lab.repo.HabitatRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitatMapper {
    private final AnimalRepository animalRepository;
    private final HabitatRepository habitatRepository;

    public List<HabitatDto> toListDto(List<Habitat> habitats) {
        return habitats.stream().map(this::toDto).toList();
    }

    public HabitatDto toDto(Habitat habitat) {
        return HabitatDto.builder()
                .id(habitat.getId())
                .name(habitat.getName())
                .description(habitat.getDescription())
                .climate(habitat.getClimate())
                .area(habitat.getArea())
                .localDateTime(habitat.getLocalDateTime())
                .animals(habitat.getAnimals().stream()
                        .map(Animal::getName)
                        .toList())
                .build();
    }

    public Habitat toEntity(HabitatDto habitatDto) {
        Habitat habitat = new Habitat();

        habitat.setId(habitatDto.getId());
        habitat.setName(habitatDto.getName());
        habitat.setDescription(habitatDto.getDescription());
        habitat.setClimate(habitatDto.getClimate());
        habitat.setArea(habitatDto.getArea());
        habitat.setAnimals((Collections.singletonList
                (animalRepository.findByName(String.valueOf(habitatDto.getAnimals().stream().toList(
                ))))));


        return habitat;
    }
}
