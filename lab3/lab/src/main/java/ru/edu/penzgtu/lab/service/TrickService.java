package ru.edu.penzgtu.lab.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.TrickDto;
import ru.edu.penzgtu.lab.entity.Trick;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.TrickRepository;
import ru.edu.penzgtu.lab.service.mapper.TrickMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrickService {
    private final TrickRepository trickRepository;
    private final TrickMapper trickMapper;


    public List<TrickDto> findAllTricks() {
        log.info("Найдены все существующие трюки в БД");
        return trickMapper.toListDto(trickRepository.findAll());
    }

    @Transactional
    public TrickDto findTrickById(Long id) {
        log.info("Найден трюк по id: " + id);
        Trick trick = trickRepository.findById(id)
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Трюк с id " + id + " на найдена"));
        return trickMapper.toDto(trick);
    }


    public List<TrickDto> findTrickByName(String name) {
        log.info("Найдены трюки по названию: " + name);
        List<Trick> tricks = trickRepository.findTrickByName(name);
        return trickMapper.toListDto(tricks);
    }

    public void createTrick(TrickDto trickDto) {
        log.info("Трюк создан");
        Trick trick = trickMapper.toEntity(trickDto);
        trick.setLocalDateTime(LocalDateTime.now());
        trickRepository.save(trick);
    }


    public void updateTrick(TrickDto newTrick) {
        log.info("Данные о трюке были обновлены");
        Trick oldTrick = trickRepository.findById(newTrick.getId()).
                orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Трюк не найден"));
        oldTrick.setName(newTrick.getName());
        oldTrick.setDescription(newTrick.getDescription());
        oldTrick.setDifficulty(newTrick.getDifficulty());
        oldTrick.setCategory(newTrick.getCategory());
        trickRepository.save(oldTrick);

    }


    public void deleteTrickById (Long id) {
        log.info("Удалён трюк с id: " + id);
        trickRepository.deleteById(id);
    }
}
