package com.melek.gestionstock.handlers;

import com.melek.gestionstock.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private  Integer httpCode;
    private ErrorCodes codes;
    private String message;
    private List<String> errors = new ArrayList<>();
}
