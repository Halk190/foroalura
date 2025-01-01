package com.challenge.foroalura.domain.topico;

import com.challenge.foroalura.domain.curso.Curso;
import com.challenge.foroalura.domain.curso.CursoRepository;
import com.challenge.foroalura.domain.topico.datos.TopicoDTO;
import com.challenge.foroalura.domain.topico.datos.TopicoResponseDTO;
import com.challenge.foroalura.domain.topico.validacion.TopicoValidateDTO;
import com.challenge.foroalura.domain.topico.validacion.TopicoValidator;
import com.challenge.foroalura.domain.usuario.Usuario;
import com.challenge.foroalura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public TopicoResponseDTO registrarTopico(TopicoDTO topicoDTO) {

        //System.out.println("nombre del curso: " + topicoDTO.idCurso());

        TopicoValidateDTO topicoValidateDTO = new TopicoValidateDTO(topicoDTO.titulo(), topicoDTO.mensaje());

        topicoValidator.validar(topicoValidateDTO);

        Usuario usuario = usuarioRepository.findById(topicoDTO.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        //Curso curso = cursoRepository.getReferenceById(topicoDTO.idCurso());

        Curso curso = cursoRepository.findByNombre(topicoDTO.nombreCurso().trim().toLowerCase())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));


        // Obtener la fecha actual de creación
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Integer respuestas = 0;

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
        return new TopicoResponseDTO(topico);
    }


}
