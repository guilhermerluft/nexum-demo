package br.com.nexum.nexum_api.repository;

import br.com.nexum.nexum_api.domain.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {
}
