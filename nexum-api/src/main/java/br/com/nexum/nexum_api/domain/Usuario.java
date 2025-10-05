package br.com.nexum.nexum_api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Integer id;

    private String nome;

    @Column(columnDefinition = "citext")
    private String email;
    private String senha;
    private boolean ativo;
    private boolean excluido;

    @JoinColumn(name = "criado_em")
    private LocalDateTime criadoEm;

    @JoinColumn(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @JoinColumn(name = "desativado_em")
    private LocalDateTime desativadoEm;

    @ManyToOne
    @JoinColumn(name = "atualizado_por")
    private Usuario atualizadoPor;

}
