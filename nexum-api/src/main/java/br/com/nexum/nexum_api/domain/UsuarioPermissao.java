package br.com.nexum.nexum_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "usuario_permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermissao extends Auditoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public Integer id;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "permissao_id")
    public Permissao permissao;

}