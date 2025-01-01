package com.challenge.foroalura.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(
        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria
) {
}
