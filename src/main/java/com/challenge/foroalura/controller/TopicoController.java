package com.challenge.foroalura.controller;

import com.challenge.foroalura.domain.topico.datos.TopicoDTO;
import com.challenge.foroalura.domain.topico.datos.TopicoResponseDTO;
import com.challenge.foroalura.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDTO>> listarTopico(@PageableDefault(size = 5,
            sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listadoTopico(paginacion));
    }
}
