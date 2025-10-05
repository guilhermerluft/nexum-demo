package br.com.nexum.nexum_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Permissao extends Auditoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Integer id;

    private String nome;
    private String descricao;
}
