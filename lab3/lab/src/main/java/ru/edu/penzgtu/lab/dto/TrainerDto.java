package ru.edu.penzgtu.lab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Schema(description = "Информация о тренере")
public class TrainerDto {

    @JsonProperty("id")
    @Schema(description = "ID тренера в БД", example = "52")
    private Long id;

    @JsonProperty("Имя")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя тренера может содержать только буквы")
    @Schema(description = "Имя тренера", example = "Джамал")
    private String name;

    @JsonProperty("Страна")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Страна тренера может содержать только буквы")
    @Schema(description = "Страна тренера", example = "Ниггерия")
    private String country;

    @JsonProperty("Возраст")
    @Schema(description = "Возраст тренера", example = "52")
    private Long yearsOld;

    @JsonProperty("Описание")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Описание тренера может содержать только буквы")
    @Schema(description = "Описание тренера",example = "Классный крутой красивый")
    private String description;

    @JsonProperty("Дата и время")
    @Schema(description = "Дата и время добавления тренера в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("Трюки")
    @Size(min = 0,max = 52,
            message = "Количество трюков должно быть от 0 до 52")
    @Schema(description = "Количество трюков у тренера")
    private List<String> tricks;

    public TrainerDto(Long id, String name, String country, Long yearsOld, String description,
                      LocalDateTime localDateTime, List<String> tricks) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.yearsOld = yearsOld;
        this.description = description;
        this.localDateTime = localDateTime;
        this.tricks = tricks;
    }
}
