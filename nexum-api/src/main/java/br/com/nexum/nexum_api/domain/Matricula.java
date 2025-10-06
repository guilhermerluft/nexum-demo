package br.com.nexum.nexum_api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class Matricula extends Auditoria{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name= "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso curso;

    @Enumerated(EnumType.STRING)
    private StatusMatricula status;

    @JoinColumn(name= "data_matricula")
    private LocalDateTime dataMatricula;

    @JoinColumn(name= "data_conclusao")
    private LocalDateTime dataConclusao;
}
