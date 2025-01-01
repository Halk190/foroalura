package com.challenge.foroalura.domain.topico.datos;

import jakarta.validation.constraints.NotNull;

public record RegistroTopicoDTO(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        Long idCurso
) {
}
