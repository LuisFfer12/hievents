package br.com.hievents.repository.evento;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hievents.entity.evento.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	@Query(value="Select u from Evento u where u.endereco=:pendereco, u.data=:pdata ")
	boolean buscaEnderecoData(@Param("pendereco") String endereco, @Param("pdata") Date data);

}
