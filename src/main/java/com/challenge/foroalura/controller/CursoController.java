package com.challenge.foroalura.controller;

import com.challenge.foroalura.domain.curso.datos.CursoDTO;
import com.challenge.foroalura.domain.curso.datos.CursoResponseDTO;
import com.challenge.foroalura.domain.curso.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoResponseDTO> registrarCurso(@RequestBody @Valid CursoDTO cursoDTO){
        CursoResponseDTO response = cursoService.registrarCurso(cursoDTO);

        return ResponseEntity.ok(response);
    }
}
