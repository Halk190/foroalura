package com.challenge.foroalura.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioAuthDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String contrasena) {
}
