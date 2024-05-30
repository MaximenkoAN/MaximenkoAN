package ru.edu.penzgtu.lab.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.dto.TrainerDto;
import ru.edu.penzgtu.lab.entity.Trainer;
import ru.edu.penzgtu.lab.exception.ErrorType;
import ru.edu.penzgtu.lab.exception.PenzGtuException;
import ru.edu.penzgtu.lab.repo.TrainerRepository;
import ru.edu.penzgtu.lab.service.mapper.TrainerMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    public List<TrainerDto> findAllTrainers(){
        log.info("Найдены все существующие тренера в БД");
        return trainerMapper.toListDto(trainerRepository.findAll());
    }

    @Transactional
    public TrainerDto findTrainerById(Long id)  {
        log.info("Найден тренер по id: " + id);
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Тренер с id " + id + " не найден"));
        return trainerMapper.toDto(trainer);
    }


    public List<TrainerDto> findTrainerByName(String name) {
        log.info("Найдены тренера по имени: " + name);
        List<Trainer> trainers = trainerRepository.findTrainerByName(name);
        return trainerMapper.toListDto(trainers);
    }


    public void createTrainer(TrainerDto trainerDto){
        log.info("Тренер сохранен");
        Trainer trainer = trainerMapper.toEntity(trainerDto);
        trainer.setLocalDateTime(LocalDateTime.now());
        trainerRepository.save(trainer);
    }


    public void updateTrainer(TrainerDto newTrainer) {
        log.info("Данные о тренере были обновлены");
        Trainer oldTrainer = trainerRepository.findById(newTrainer.getId())
                .orElseThrow(() ->new PenzGtuException(ErrorType.NOT_FOUND,"Тренер не найден"));
        oldTrainer.setName(newTrainer.getName());
        oldTrainer.setCountry((newTrainer.getCountry()));
        oldTrainer.setYearsOld(newTrainer.getYearsOld());
        oldTrainer.setDescription(newTrainer.getDescription());
        trainerRepository.save(oldTrainer);
    }


    public void deleteTrainerById(Long id ) {
        log.info("Удалён тренер с id: " + id);
        trainerRepository.deleteById(id);
    }

}
