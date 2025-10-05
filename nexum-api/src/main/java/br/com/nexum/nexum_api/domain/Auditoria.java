package br.com.nexum.nexum_api.domain;

import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auditoria {

    private boolean Ativo;
    private boolean Excluido;

    @JoinColumn(name= "criado_em")
    private LocalDateTime criadoEm;

    @JoinColumn(name= "criado_por")
    private Usuario criadoPor;

    @JoinColumn(name= "atualizado_em")
    private LocalDateTime atualizadoEm;

    @JoinColumn(name = "atualizado_por")
    private Usuario atualizadoPor;

}
