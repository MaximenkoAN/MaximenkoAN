package ru.edu.penzgtu.lab.baseresponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private String code;
    private String title;
    private String text;
}
