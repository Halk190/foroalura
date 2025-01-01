package com.challenge.foroalura.controller;

import com.challenge.foroalura.domain.topico.TopicoDTO;
import com.challenge.foroalura.domain.topico.TopicoResponseDTO;
import com.challenge.foroalura.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService; // Inyectamos el servicio de Topico

    @PostMapping
    public ResponseEntity<TopicoResponseDTO> registrarTopico(@RequestBody @Valid TopicoDTO topicoDTO) {
        // Usamos el servicio para registrar el tópico y obtener la respuesta
        TopicoResponseDTO response = topicoService.registrarTopico(topicoDTO);

        // Retornamos la respuesta como un ResponseEntity
        return ResponseEntity.ok(response);
    }
}
