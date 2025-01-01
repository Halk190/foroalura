package com.challenge.foroalura.domain.topico.datos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long idUsuario,
        @NotBlank
        String nombreCurso
        //@NotNull
        //Long idCurso
) {
}
