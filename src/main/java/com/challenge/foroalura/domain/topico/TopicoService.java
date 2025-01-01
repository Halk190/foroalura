package com.challenge.foroalura.domain.topico;

import com.challenge.foroalura.domain.ValidacionException;
import com.challenge.foroalura.domain.curso.Curso;
import com.challenge.foroalura.domain.curso.CursoRepository;
import com.challenge.foroalura.domain.topico.datos.TopicoDTO;
import com.challenge.foroalura.domain.topico.datos.TopicoResponseDTO;
import com.challenge.foroalura.domain.topico.validacion.TopicoValidateDTO;
import com.challenge.foroalura.domain.topico.validacion.TopicoValidator;
import com.challenge.foroalura.domain.usuario.Perfil;
import com.challenge.foroalura.domain.usuario.Usuario;
import com.challenge.foroalura.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public Page<TopicoResponseDTO> listadoTopico(Pageable pageable) {
        Page<Topico> topicos = topicoRepository.findAll(pageable);

        return topicos.map(TopicoResponseDTO::new);
    }

    public Page<TopicoResponseDTO> listadoTopicosPorCurso(String nombre, Pageable pageable) {
        Page<Topico> topicos = topicoRepository.findAllByCursoNombre(nombre, pageable);

        return topicos.map(TopicoResponseDTO::new);
    }

    public String eliminarTopico(Long id, Authentication authentication) {
        // Obtener el usuario actual desde la autenticación
        Usuario usuarioActual = (Usuario) authentication.getPrincipal();

        // Buscar el tópico por id
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        // Verificar si el usuario actual es el creador del tópico o si es administrador
        if (!esUsuarioAutorizado(usuarioActual, topico)) {
            throw new AccessDeniedException("No tienes permisos para eliminar este tópico");
        }

        // Eliminar el tópico
        topicoRepository.delete(topico);

        // Devolver el mensaje adecuado según quien realizó la eliminación
        if (usuarioActual.getPerfil() == Perfil.ADMIN) {
            return "El tópico fue eliminado por un administrador.";
        } else {
            return "El tópico fue eliminado por su autor.";
        }
    }

    private boolean esUsuarioAutorizado(Usuario usuarioActual, Topico topico) {
        // Verificar si el usuario es ADMIN o si es el creador del tópico
        return usuarioActual.getPerfil() == Perfil.ADMIN || usuarioActual.getId().equals(topico.getAutor().getId());
    }

}
