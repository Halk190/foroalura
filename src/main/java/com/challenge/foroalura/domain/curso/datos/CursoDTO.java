package com.challenge.foroalura.domain.curso.datos;

import com.challenge.foroalura.domain.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(
        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria
) {
}
