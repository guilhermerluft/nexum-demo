package br.com.nexum.nexum_api.repository;

import br.com.nexum.nexum_api.domain.UsuarioPermissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao, Integer> {
}
