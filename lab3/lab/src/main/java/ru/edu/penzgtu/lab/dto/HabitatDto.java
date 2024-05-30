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
@Schema(description = "Информация о среде обитания")
public class HabitatDto {

    @JsonProperty("id")
    @Schema(description = "ID среды обитания в БД", example = "123")
    private Long id;

    @JsonProperty("Название")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название среды обитания может содержать только буквы")
    @Schema(description = "Название среды обитания", example = "Тайга")
    private String name;

    @JsonProperty("Описание")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Описание среды обитания может содержать только буквы")
    @Schema(description = "Описание среды обитания", example = "Опасно и классно")
    private String description;

    @JsonProperty("Климат")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Климат среды обитания может содержать только буквы")
    @Schema(description = "Климат среды обитания", example = "Экваториальный")
    private String climate;

    @JsonProperty("Площадь")
    @Schema(description = "Площадь сред обитания в кв. км.", example = "150")
    private Long area;

    @JsonProperty("Дата и время")
    @Schema(description = "Дата и время добавления среды обитания в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("Животные")
    @Size(min = 0,max = 52,
            message = "Количество животных должно быть от 0 до 52")
    @Schema(description = "Название животных")
    private List<String> animals;

    public HabitatDto(Long id, String name, String description, String climate,
                      Long area, LocalDateTime localDateTime, List<String> animals) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.climate = climate;
        this.area = area;
        this.localDateTime = localDateTime;
        this.animals = animals;
    }
}
