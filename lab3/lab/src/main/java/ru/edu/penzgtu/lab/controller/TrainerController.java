package ru.edu.penzgtu.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.lab.baseresponse.BaseResponseService;
import ru.edu.penzgtu.lab.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.lab.dto.TrainerDto;
import ru.edu.penzgtu.lab.service.TrainerService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/trainers")
@RequiredArgsConstructor
@Tag(name = "Тренеры", description = "Операции над тренерами")
public class TrainerController {

    private final TrainerService trainerService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех тренеров",
            description = "Позволяет выгрузить всех тренеров из БД"
    )

    @GetMapping
    public ResponseWrapper<List<TrainerDto>> findAll(){
        return
                baseResponseService.wrapSuccessResponse(trainerService.findAllTrainers());
    }

    @Operation(
            summary = "Получение тренера по ID",
            description = "Позволяет выгрузить одного тренера по ID из БД"
    )
    @GetMapping("/{id}")
    public ResponseWrapper<TrainerDto> getById(@PathVariable @Min(0) Long id)  {
        return
                baseResponseService.wrapSuccessResponse(trainerService.findTrainerById(id));
    }

    @Operation(
            summary = "Найти тренера по имени",
            description = "Позволяет найти тренера по имени в БД"
    )
    @GetMapping("/byName")
    public ResponseWrapper<List<TrainerDto>> getByName(@RequestParam String name) {
        return
                baseResponseService.wrapSuccessResponse(trainerService.findTrainerByName(name));
    }

    @Operation(
            summary = "Создать тренера",
            description = "Позволяет создать новую запись о тренере в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainer(@RequestBody @Valid TrainerDto trainer){
        trainerService.createTrainer(trainer);
    }

    @Operation(
            summary = "Обновить данные о тренере",
            description = "Позволяет обновить информацию о тренере в БД"
    )
    @Transactional
    @PutMapping("/")
    public void updateTrainer(@RequestBody  @Valid TrainerDto trainer) {
        trainerService.updateTrainer(trainer);
    }

    @Operation(
            summary = "Удалить тренера по ID",
            description = "Позволяет удалить тренера по ID из БД"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable @Min(0) Long id) {
        trainerService.deleteTrainerById(id);
    }
}