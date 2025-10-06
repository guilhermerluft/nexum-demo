package br.com.nexum.nexum_api.service;

import br.com.nexum.nexum_api.domain.Usuario;
import br.com.nexum.nexum_api.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class UsuarioAutenticadoService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioAutenticadoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        return jwt.getClaim("email");
    }

    public Usuario get() {
        return usuarioRepository.findByEmail(getEmail())
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "Usuário não existe ou não está autenticado"));
    }
}
