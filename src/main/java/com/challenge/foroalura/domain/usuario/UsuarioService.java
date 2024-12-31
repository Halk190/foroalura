package com.challenge.foroalura.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void crearUsuario(String nombre, String password, String email,Perfil perfil) {
        String passwordEncriptada = passwordEncoder.encode(password); // Encriptamos la contraseña con BCrypt
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setContrasena(passwordEncriptada);
        usuario.setCorreoElectronico(email);
        usuario.setPerfil(perfil);
        usuarioRepository.save(usuario);
    }


}
