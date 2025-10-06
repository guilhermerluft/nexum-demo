package br.com.nexum.nexum_api.service;

import br.com.nexum.nexum_api.domain.Usuario;
import br.com.nexum.nexum_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscarUsuarioPorEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario get(String email) {

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Usuário não encontrado" ));
    }
}

