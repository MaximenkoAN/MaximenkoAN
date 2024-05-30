package ru.edu.penzgtu.lab.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.AnimalDto;
import ru.edu.penzgtu.lab.entity.Habitat;
import ru.edu.penzgtu.lab.entity.Animal;
import ru.edu.penzgtu.lab.repo.HabitatRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalMapper {
    private final HabitatRepository habitatRepository;


    public List<AnimalDto> toListDto(List<Animal> animals) {
        return animals.stream().map(this::toDto).toList();
    }

    public AnimalDto toDto(Animal animal) {
        String habitatName = animal.getHabitat() != null ?
                animal.getHabitat().getName() : null;
        return AnimalDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .yearsOld(animal.getYearsOld())
                .gender(animal.getGender())
                .color(animal.getColor())
                .localDateTime(animal.getLocalDateTime())
                .habitatName(habitatName)
                .build();
    }

    public Animal toEntity(AnimalDto animalDto) {
        Animal animal = new Animal();

        animal.setId(animalDto.getId());
        animal.setName(animalDto.getName());
        animal.setYearsOld(animalDto.getYearsOld());
        animal.setGender(animalDto.getGender());
        animal.setColor(animalDto.getColor());
        animal.setLocalDateTime(animalDto.getLocalDateTime());
        animal.setHabitat(habitatRepository.findByName(animalDto.getHabitatName()));

        return animal;
    }
}
