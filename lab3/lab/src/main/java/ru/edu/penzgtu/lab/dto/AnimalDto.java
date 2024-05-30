package ru.edu.penzgtu.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Информация о животном")
public class AnimalDto {

    @JsonProperty("id")
    @Schema(description = "ID животного в БД", example = "123")
    private Long id;

    @JsonProperty("Название")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название животного может содержать только буквы и пробелы")
    @Schema(description = "Название животного", example = "Волк")
    private String name;

    @JsonProperty("Возраст")
    @Schema(description = "Возраст животного",example = "5")
    private Long yearsOld;


    @JsonProperty("Гендер")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Гендер животного может содержать только буквы и пробелы")
            @Schema(description = "Гендер студии", example = "Мужской")
    private String gender;

    @JsonProperty("Цвет")
    @NotBlank
    @Schema(description = "Окрас животного", example = "Чёрный")
    private String color;

    @JsonProperty("Дата и время")
    @Schema(description = "Дата и время добавления животного в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("Среда обитания")
    @Schema(description = "Название среды обитания")
    private String habitatName;

    public AnimalDto(Long id, String name, Long yearsOld, String gender, String color,
                     LocalDateTime localDateTime, String habitatName) {
        this.id = id;
        this.name = name;
        this.yearsOld = yearsOld;
        this.localDateTime = localDateTime;
        this.gender = gender;
        this.color = color;
        this.habitatName = habitatName;
    }
}
