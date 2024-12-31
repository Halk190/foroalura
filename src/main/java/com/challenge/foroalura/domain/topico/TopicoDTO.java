package com.challenge.foroalura.domain.topico;

public record TopicoDTO(
        Long idUsuario,
        String mensaje,
        String nombreCurso,
        String titulo
) {
}
