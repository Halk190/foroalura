package com.challenge.foroalura.domain.topico.datos;

import jakarta.validation.constraints.NotBlank;

public record TopicoUpdateDTO(
        String titulo,
        String mensaje,
        Boolean status
) {
}
