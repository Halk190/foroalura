package com.challenge.foroalura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        LocalDateTime fechadecreacion,
        Boolean status,
        @NotNull
        Long idUsuario,
        @NotBlank
        String nombreCurso
) {
}
