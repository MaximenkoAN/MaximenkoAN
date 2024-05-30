package ru.edu.penzgtu.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Информация о трюке")
public class TrickDto {

    @JsonProperty("id")
    @Schema(description = "ID трюка в БД", example = "52")
    private Long id;


    @JsonProperty("Название")
    @Schema(description = "Название трюка в БД", example = "Сальто с переподвыпердом")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название трюка может содержать только буквы")
    private String name;


    @JsonProperty("Описание")
    @Schema(description = "Описание трюка", example = "Любой человек удивиться")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Описание трюка может содержать только буквы")
    private String description;


    @JsonProperty("Категория")
    @Schema(description = "Категория трюка", example = "Ловкость")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Категория трюка может содержать только буквы")
    private String category;

    @JsonProperty("Сложность")
    @Schema(description = "Сложность трюка", example = "5")
    private Long difficulty;

    @JsonProperty("Дата и время")
    @Schema(description = "Дата и время добавления трюка в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("Тренер")
    @Schema(description = "Имя тренера, который придумал трюк", example = "Геге")
    private String trainerName;

    public TrickDto(Long id, String name, String description, String category,
                    Long difficulty, LocalDateTime localDateTime, String trainerName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.localDateTime = localDateTime;
        this.trainerName = trainerName;
    }
}
