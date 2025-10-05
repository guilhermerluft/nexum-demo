package br.com.nexum.nexum_api.domain;


import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Aula extends Auditoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String titulo;

    @JoinColumn(name= "video_url")
    private String videoUrl;
    private String descricao;

    @ManyToOne
    @JoinColumn(name= "curso_id")
    private Curso curso;

    @JoinColumn(name="ordem_index")
    private Integer ordemIndex;
}
