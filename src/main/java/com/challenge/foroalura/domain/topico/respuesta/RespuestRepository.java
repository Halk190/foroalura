package com.challenge.foroalura.domain.topico.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestRepository extends JpaRepository<Respuesta,Long> {
}
