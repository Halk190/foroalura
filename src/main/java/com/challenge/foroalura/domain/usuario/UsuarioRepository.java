package com.challenge.foroalura.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    UserDetails findByNombre(String nombre);

    //boolean existsByIdAndRolesNombre(Long id, Perfil perfil);

}
