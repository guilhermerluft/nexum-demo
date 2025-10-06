package br.com.nexum.nexum_api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter @Setter
public class Progresso extends Auditoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name= "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    private boolean completado;

    @JoinColumn(name= "completado_em")
    private LocalDateTime completadoEm;
}
