package br.com.nexum.nexum_api.repository;

import br.com.nexum.nexum_api.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
