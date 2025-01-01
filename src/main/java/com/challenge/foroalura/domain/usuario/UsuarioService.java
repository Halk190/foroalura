package com.challenge.foroalura.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Value("${ADMIN_KEY}") // Inyecta la variable de entorno
    private String adminKey;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        // Validación de la clave
        String clave = usuarioDTO.clave();
        Perfil perfil = Perfil.ESTUDIANTE; // Default profile

        if (adminKey.equals(clave)) {
            perfil = Perfil.ADMIN; // Si la clave es correcta, asignamos el perfil de admin
        }

        // Crear el usuario
        Usuario usuario = new Usuario(
                usuarioDTO.nombre(),
                usuarioDTO.correoElectronico(),
                passwordEncoder.encode(usuarioDTO.contrasena()), // Encriptamos la contraseña
                perfil
        );

        // Guardamos el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }
}
