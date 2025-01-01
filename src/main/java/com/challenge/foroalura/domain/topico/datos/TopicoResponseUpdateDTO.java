package com.challenge.foroalura.domain.topico.datos;

import com.challenge.foroalura.domain.curso.datos.CursoResponseDTO;
import com.challenge.foroalura.domain.topico.Topico;

import java.time.LocalDateTime;

public record TopicoResponseUpdateDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechadecreacion,
        Boolean status,
        CursoResponseDTO cursoResponse
) {
    public TopicoResponseUpdateDTO(Topico topico){
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
                new CursoResponseDTO(topico.getId(),topico.getCurso().getNombre(), topico.getCurso().getCategoria().toString()));
    }
}
