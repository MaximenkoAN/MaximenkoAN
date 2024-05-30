package ru.edu.penzgtu.lab.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.AnimalDto;
import ru.edu.penzgtu.lab.entity.Animal;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.AnimalRepository;
import ru.edu.penzgtu.lab.service.mapper.AnimalMapper;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    @Transactional
    public List<AnimalDto> findAllAnimals(){
        log.info("Найдены все существующие животные в БД");
        return animalMapper.toListDto(animalRepository.findAll());
    }

    @Transactional
    public AnimalDto findAnimalById(Long id)  {
        log.info("Найдено животное по id: " + id);
        Animal animal = animalRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Животное с id " + id + " не найдено"));
        return animalMapper.toDto(animal);
    }


    public List<AnimalDto> findAnimalByName(String name) {
        log.info("Найдено животное по названию: " + name);
        List<Animal> animals = animalRepository.findAnimalByName(name);
        return animalMapper.toListDto(animals);
    }

    public void createAnimal(AnimalDto animalDto){
        log.info("Животное создано");
        Animal animal = animalMapper.toEntity(animalDto);
        animal.setLocalDateTime(LocalDateTime.now());
        animalRepository.save(animal);
    }

    public void updateAnimal(AnimalDto newAnimal) {
        log.info("Данные о животном были обновлены");
        Animal oldAnimal = animalRepository.findById(newAnimal.getId())
                .orElseThrow(() ->new PenzGtuException(ErrorType.NOT_FOUND,"Животное не найдено"));
        oldAnimal.setName(newAnimal.getName());
        oldAnimal.setYearsOld(newAnimal.getYearsOld());
        oldAnimal.setGender(newAnimal.getGender());
        oldAnimal.setColor(oldAnimal.getColor());
        animalRepository.save(oldAnimal);
    }


    public void deleteAnimalById(Long id) {
        log.info("Удалено животное с id: " + id);
        animalRepository.deleteById(id);
    }

}

