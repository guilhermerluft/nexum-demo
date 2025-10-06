package br.com.nexum.nexum_api.controller.response;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponse {

    private Integer id;
    private String nomeCompleto;
    private String email;

}
