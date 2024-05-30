package ru.edu.penzgtu.lab.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.lab.baseresponse.BaseResponseService;
import ru.edu.penzgtu.lab.baseresponse.ResponseWrapper;
import ru.edu.penzgtu.lab.dto.AnimalDto;
import ru.edu.penzgtu.lab.repo.AnimalRepository;
import ru.edu.penzgtu.lab.service.AnimalService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
@Tag(name = "Животные", description = "Операции над животными")
public class AnimalController {

    private final AnimalService animalService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех животных",
            description = "Позволяет выгрузить всех животных из БД"
    )
    @GetMapping
    public ResponseWrapper<List<AnimalDto>> findAll() {
        return
                baseResponseService.wrapSuccessResponse(animalService.findAllAnimals());
    }

    @Operation(
            summary = "Получение животного по ID",
            description = "Позволяет выгрузить одного животного по ID из БД"
    )
    @GetMapping("/{id}")
    public ResponseWrapper<AnimalDto> getById(@PathVariable @Min(0) Long id) {
        return
                baseResponseService.wrapSuccessResponse(animalService.findAnimalById(id));
    }
    @Operation(
            summary = "Получение животного по названию",
            description = "Позволяет выгрузить одного животного по названию из БД"
    )
    @GetMapping("/byName")
    public ResponseWrapper <List<AnimalDto>> findAnimalByName(@RequestParam String name) {
        return
                baseResponseService.wrapSuccessResponse(animalService.findAnimalByName(name));
    }

    @Operation(
            summary = "Создать животное",
            description = "Позволяет создать новую запись о животном в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnimal(@RequestBody @Valid AnimalDto animal) {
        animalService.createAnimal(animal);
    }


    @Operation(
            summary = "Обновить данные о животном",
            description = "Позволяет обновить информацию о животном в БД"
    )
    @PutMapping("/")
    public void updateAnimal(@RequestBody @Valid AnimalDto animal) {
        animalService.updateAnimal(animal);
    }


    @Operation(
            summary = "Удалить животное по ID",
            description = "Позволяет удалить животного по ID из БД"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnimal(@PathVariable @Min(0) Long id) {
        animalService.deleteAnimalById(id);
    }
}
