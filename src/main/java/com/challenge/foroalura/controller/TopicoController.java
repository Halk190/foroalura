package com.challenge.foroalura.controller;

import com.challenge.foroalura.domain.topico.datos.TopicoDTO;
import com.challenge.foroalura.domain.topico.datos.TopicoResponseDTO;
import com.challenge.foroalura.domain.topico.TopicoService;
import com.challenge.foroalura.domain.topico.datos.TopicoResponseUpdateDTO;
import com.challenge.foroalura.domain.topico.datos.TopicoUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<Page<TopicoResponseDTO>> listarTopico(@PageableDefault(size = 10,
            sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listadoTopico(paginacion));
    }

    //Listar Topicos por Curso
    @GetMapping("/curso/{nombreCurso}")
    public ResponseEntity<Page<TopicoResponseDTO>> listarTopicosPorCurso(@PageableDefault(size = 5,sort = "fechaCreacion", direction = Sort.Direction.ASC)
                                                                   Pageable paginacion,@PathVariable String nombreCurso){
        return ResponseEntity.ok(topicoService.listadoTopicosPorCurso(nombreCurso, paginacion));
    }

    //Detalles Topico
    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> detallesTopico(@PathVariable Long id){
        return ResponseEntity.ok(topicoService.detalleTopico(id));
    }

    //Actualizar Topico
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<TopicoResponseUpdateDTO> actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoUpdateDTO datos,Authentication authentication){
        return ResponseEntity.ok(topicoService.actualizar(id,datos,authentication));
    }

    //Eliminar Topico
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id, Authentication authentication) {
        String mensaje = topicoService.eliminarTopico(id, authentication);
        return ResponseEntity.ok(mensaje);
    }
}
