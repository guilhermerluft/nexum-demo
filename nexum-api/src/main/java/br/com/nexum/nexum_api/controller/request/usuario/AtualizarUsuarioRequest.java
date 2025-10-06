package br.com.nexum.nexum_api.controller.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class AtualizarUsuarioRequest {


    @NotNull
    private String nomeCompleto;

    @NotNull @Email
    private String email;

}
