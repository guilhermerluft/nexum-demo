package br.com.nexum.nexum_api.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Curso extends Auditoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String titulo;
    private String descricao;
    private String categoria;

    @JoinColumn(name= "por_assinatura")
    private boolean porAssinatura;

    @ManyToOne
    @JoinColumn(name= "instrutor_id")
    private Usuario instrutorId;
}
