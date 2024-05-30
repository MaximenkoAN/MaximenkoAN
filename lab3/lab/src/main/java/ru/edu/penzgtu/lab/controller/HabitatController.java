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
import ru.edu.penzgtu.lab.dto.HabitatDto;
import ru.edu.penzgtu.lab.service.HabitatService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/habitats")
@RequiredArgsConstructor
@Tag(name = "Среды обитания", description = "Операции над средами обитания")
public class HabitatController {
    private final HabitatService habitatService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех сред обитания",
            description = "Позволяет выгрузить все среды обитания из БД")
    @GetMapping
    public ResponseWrapper<List<HabitatDto>> findAllHabitat() {
        return
                baseResponseService.wrapSuccessResponse(habitatService.findAllHabitats());
    }

    @Operation(
            summary = "Получение среды обитания по ID",
            description = "Позволяет выгрузить одну среду обитания по ID из БД")
    @GetMapping("/{id}")
    public ResponseWrapper<HabitatDto> getHabitatById(@PathVariable @Min(0) Long id) {
        return
                baseResponseService.wrapSuccessResponse(habitatService.findHabitatById(id));
    }

    @Operation(
            summary = "Получение среды обитания по названию",
            description = "Позволяет найти аниме по названию в БД"
    )
    @GetMapping("/byTitle")
    public ResponseWrapper<List<HabitatDto>> getByName(@RequestParam @Valid String name) {
        return baseResponseService.wrapSuccessResponse(habitatService.findHabitatByName(name));
    }

    @Operation(
            summary = "Создать среду обитания",
            description = "Позволяет создать новую запись о среде обитания в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createHabitat(@RequestBody @Valid HabitatDto habitat) {
        habitatService.saveHabitat(habitat);
    }

    @Operation(
            summary = "Обновить данные о среде обитания",
            description = "Позволяет обновить информацию о среде обитания в БД")
    @PutMapping("/")
    public void updateHabitat(@RequestBody @Valid HabitatDto habitat) {
        habitatService.updateHabitat(habitat);
    }

    @Operation(
            summary = "Удалить среду обитания по ID ",
            description = "Позволяет удалить среду обитания по ID из БД")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHabitat(@PathVariable @Min(0) Long id) {
        habitatService.deleteHabitatById(id);
    }
}
