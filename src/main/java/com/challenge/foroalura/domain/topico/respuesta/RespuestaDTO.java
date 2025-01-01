package com.challenge.foroalura.domain.topico.respuesta;

public record RespuestaDTO(
        String mensaje,
        Long topico_id,
        Long autor_id
) {
}
