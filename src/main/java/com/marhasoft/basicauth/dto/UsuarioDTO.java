package com.marhasoft.basicauth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    private Set<String> roles = new HashSet<>();
}
