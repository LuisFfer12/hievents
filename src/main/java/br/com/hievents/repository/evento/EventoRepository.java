package br.com.hievents.repository.evento;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hievents.entity.evento.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
