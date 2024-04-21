package br.com.kaua.mostratempo.repository;

import br.com.kaua.mostratempo.model.ConsultaTempo;
import br.com.kaua.mostratempo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaTempoRepository extends JpaRepository<ConsultaTempo, Long> {
    List<ConsultaTempo> findByUsuario(Usuario usuario);
}
