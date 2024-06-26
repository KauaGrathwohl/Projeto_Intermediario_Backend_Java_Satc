package br.com.kaua.mostratempo.repository;

import br.com.kaua.mostratempo.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByNome(String nome);
}
