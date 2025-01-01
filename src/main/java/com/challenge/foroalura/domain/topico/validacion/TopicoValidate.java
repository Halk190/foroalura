package com.challenge.foroalura.domain.topico.validacion;

import com.challenge.foroalura.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoValidate implements TopicoValidator{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(TopicoValidateDTO topicoValidateDTO) {
        // Verificar si ya existe un tópico con el mismo título y mensaje
        boolean existeTopico = topicoRepository.existsByTituloAndMensaje(topicoValidateDTO.titulo(), topicoValidateDTO.mensaje());
        if (existeTopico) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }
    }
}
