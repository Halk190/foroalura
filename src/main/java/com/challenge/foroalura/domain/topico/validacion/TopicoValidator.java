package com.challenge.foroalura.domain.topico.validacion;

import com.challenge.foroalura.domain.topico.validacion.TopicoValidateDTO;
import org.springframework.context.annotation.Bean;

public interface TopicoValidator {
    void validar(TopicoValidateDTO topicoValidateDTO);
}
