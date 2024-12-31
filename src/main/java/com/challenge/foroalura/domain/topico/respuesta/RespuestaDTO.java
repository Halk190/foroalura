package com.challenge.foroalura.domain.topico.respuesta;

import com.challenge.foroalura.domain.topico.TopicoDTO;

public record RespuestaDTO(
        String mensaje,
        Long topico_id,
        Long autor_id
) {
}
