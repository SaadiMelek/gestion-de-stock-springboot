package com.melek.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangePasswordUtilisateurDto {
    private Integer id;
    //private String email;
    private String password;
    private String confirmPassword;
}
