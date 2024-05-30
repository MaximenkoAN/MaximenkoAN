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
import ru.edu.penzgtu.lab.dto.TrickDto;
import ru.edu.penzgtu.lab.service.TrickService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/tricks")
@Tag(name = "Трюки",description = "Операции над трюками")
@RequiredArgsConstructor
public class TrickController {

    private final TrickService trickService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех трюков",
            description = "Позволяет выгрузить все трюки из БД"
    )
    @GetMapping
    public ResponseWrapper<List<TrickDto>> findAll(){
        return
                baseResponseService.wrapSuccessResponse(trickService.findAllTricks());
    }


    @Operation(
            summary = "Получение трюка по ID",
            description = "Позволяет выгрузить один трюк по ID из БД"
    )
    @GetMapping("/{id}")
    public ResponseWrapper<TrickDto> getById(@PathVariable @Min(0) Long id) {
        return
                baseResponseService.wrapSuccessResponse(trickService.findTrickById(id));
    }

    @Operation(
            summary = "Получение трюка по названию",
            description = "Позволяет найти трюк по названию из БД"
    )
    @GetMapping("/byTitle")
    public ResponseWrapper<List<TrickDto>> getByName(@RequestParam String name) {
        return baseResponseService.wrapSuccessResponse(trickService.findTrickByName(name));
    }

    @Operation(
            summary = "Создать трюк",
            description = "Позволяет создать новую запись о трюке в БД"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrick(@RequestBody @Valid TrickDto trick){
        trickService.createTrick(trick);
    }
    @Operation(
            summary = "Обновить данные о трюке",
            description = "Позволяет обновить информацию о трюке в БД"
    )
    @Transactional
    @PutMapping("/")
    public void updateTrick(@RequestBody @Valid TrickDto trick) {
        trickService.updateTrick(trick);
    }

    @Operation(
            summary = "Удалить трюк по ID",
            description = "Позволяет удалить трюк по ID из БД"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrick(@PathVariable @Min(0) Long id) {
        trickService.deleteTrickById(id);
    }
}
