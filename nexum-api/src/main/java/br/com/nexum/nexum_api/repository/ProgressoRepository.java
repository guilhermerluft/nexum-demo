package br.com.nexum.nexum_api.repository;

import br.com.nexum.nexum_api.domain.Progresso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressoRepository extends JpaRepository<Progresso, Integer> {
}
