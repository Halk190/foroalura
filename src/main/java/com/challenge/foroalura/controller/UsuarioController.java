package com.challenge.foroalura.controller;

import com.challenge.foroalura.domain.usuario.Usuario;
import com.challenge.foroalura.domain.usuario.UsuarioDTO;
import com.challenge.foroalura.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> crearUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.crearUsuario(usuarioDTO);

            String mensaje = "Usuario creado con éxito. Rol asignado: " + usuario.getPerfil()
                    + " El id del usuario es: " + usuario.getId();
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
