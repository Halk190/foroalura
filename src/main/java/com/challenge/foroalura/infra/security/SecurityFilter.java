
package com.challenge.foroalura.infra.security;

import com.challenge.foroalura.domain.topico.TopicoRepository;
import com.challenge.foroalura.domain.usuario.Perfil;
import com.challenge.foroalura.domain.usuario.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.challenge.foroalura.domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private TopicoRepository topicoRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        //System.out.println("Llego el token? " + tokenJWT);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            //System.out.println("tengo el subject? " + subject);
            var usuario = usuarioRepo.findByNombre(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                    usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
