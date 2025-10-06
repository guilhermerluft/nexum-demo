package br.com.nexum.nexum_api.service;

import br.com.nexum.nexum_api.controller.response.UsuarioResponse;
import br.com.nexum.nexum_api.domain.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.nexum.nexum_api.mapper.UsuarioMapper.toResponse;

@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;


    public UsuarioResponse buscarAutenticado() {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        return toResponse(usuarioAutenticado);
    }

    public Usuario buscarPorEmail(@NotNull @Email String email) {
        return buscarUsuarioPorEmailService.get(email);
    }
}
