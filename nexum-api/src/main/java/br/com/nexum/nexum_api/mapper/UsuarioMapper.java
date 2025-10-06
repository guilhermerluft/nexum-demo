package br.com.nexum.nexum_api.mapper;

import br.com.nexum.nexum_api.controller.request.usuario.IncluirUsuarioRequest;
import br.com.nexum.nexum_api.controller.response.UsuarioResponse;
import br.com.nexum.nexum_api.domain.Usuario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioMapper {

    public static Usuario toEntity(IncluirUsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setSenha(request.getSenha());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        return UsuarioResponse.builder()
                .id(entity.getId())
                .nomeCompleto(entity.getNome())
                .email(entity.getEmail())
                .build();
    }
}
