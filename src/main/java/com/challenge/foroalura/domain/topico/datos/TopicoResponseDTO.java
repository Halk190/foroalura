package com.challenge.foroalura.domain.topico.datos;

import com.challenge.foroalura.domain.curso.datos.CursoResponseDTO;
import com.challenge.foroalura.domain.topico.Topico;

import java.time.LocalDateTime;

public record TopicoResponseDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechadecreacion,
        Boolean status,
        String nombreautor,
        CursoResponseDTO cursoResponse) {
    public TopicoResponseDTO(Topico topico){
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
                topico.getAutor().getNombre(),
                new CursoResponseDTO(topico.getId(),topico.getCurso().getNombre(), topico.getCurso().getCategoria().toString()));
    }
}
