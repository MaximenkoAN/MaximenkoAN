package ru.edu.penzgtu.lab.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.HabitatDto;
import ru.edu.penzgtu.lab.entity.Habitat;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.HabitatRepository;
import ru.edu.penzgtu.lab.service.mapper.HabitatMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HabitatService {
    private final HabitatRepository habitatRepository;
    private final HabitatMapper habitatMapper;

    public List<HabitatDto> findAllHabitats() {
        log.info("Найдены все существующие среды обитания в БД");
        return habitatMapper.toListDto(habitatRepository.findAll());
    }

    @Transactional
    public HabitatDto findHabitatById(Long id) {
        log.info("Найдена среда обитания по id: " + id);
        Habitat habitat = habitatRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Среда обитания с id " + id + " не найдена"));
        return habitatMapper.toDto(habitat);
    }

    public List<HabitatDto> findHabitatByName(String name) {
        log.info("Найдены среды обитания по названию: " + name);
        List<Habitat> habitats = habitatRepository.findHabitatByName(name);
        return habitatMapper.toListDto(habitats);
    }

    public void saveHabitat( HabitatDto habitatDto) {
        log.info("Среда обитания сохранена");
        Habitat anime = habitatMapper.toEntity(habitatDto);
        anime.setLocalDateTime(LocalDateTime.now());
        habitatRepository.save(anime);
    }

    public void updateHabitat( HabitatDto newHabitat) {
        log.info("Данные о среде обитания были обновлены");
        Habitat oldHabitat = habitatRepository.findById(newHabitat.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Среда обитания не найдена"));
        oldHabitat.setName(newHabitat.getName());
        oldHabitat.setDescription(newHabitat.getDescription());
        oldHabitat.setClimate(newHabitat.getClimate());
        oldHabitat.setArea(newHabitat.getArea());
        habitatRepository.save(oldHabitat);
    }

    public void deleteHabitatById( Long id) {
        log.info("Удалена среда обитания с id: " + id);
        habitatRepository.deleteById(id);
    }
}
