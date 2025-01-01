package com.challenge.foroalura.domain.topico;

import java.time.LocalDateTime;

public record TopicoResponseDTO(
        Long id,
        String titulo,
        String mensaje,
        String nombreautor,
        String nombrecurso,
        LocalDateTime fechadecreacion
) {
}
