package com.challenge.foroalura.domain.topico;

import com.challenge.foroalura.domain.curso.Curso;
import com.challenge.foroalura.domain.curso.CursoRepository;
import com.challenge.foroalura.domain.topico.validacion.TopicoValidateDTO;
import com.challenge.foroalura.domain.topico.validacion.TopicoValidator;
import com.challenge.foroalura.domain.usuario.Usuario;
import com.challenge.foroalura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TopicoService {

    @Autowired
    private TopicoValidator topicoValidator;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoResponseDTO registrarTopico(TopicoDTO topicoDTO) {
        // Crear el TopicoValidateDTO con solo el título y el mensaje para validación
        TopicoValidateDTO topicoValidateDTO = new TopicoValidateDTO(topicoDTO.titulo(), topicoDTO.mensaje());

        // Validar antes de proceder con el registro
        topicoValidator.validar(topicoValidateDTO);

        // Obtener el Usuario desde el repositorio por ID
        Usuario usuario = usuarioRepository.findById(topicoDTO.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener el Curso desde el repositorio por nombre
        Curso curso = cursoRepository.findByNombre(topicoDTO.nombreCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Obtener la fecha actual de creación
        LocalDateTime fechaCreacion = LocalDateTime.now();

        // Crear el nuevo tópico con todos los datos del DTO
        Topico topico = new Topico(
                topicoDTO.titulo(),
                topicoDTO.mensaje(),
                fechaCreacion,
               true,
                usuario,
                curso
        );

        // Guardar el tópico en la base de datos
        topicoRepository.save(topico);

        // Crear el DTO de respuesta
        return new TopicoResponseDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                usuario.getUsername(),
                curso.getNombre(),
                fechaCreacion
        );
    }


}
