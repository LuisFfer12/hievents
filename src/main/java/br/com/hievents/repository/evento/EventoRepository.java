package br.com.hievents.repository.evento;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hievents.entity.evento.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	
	@Query(value="select count(*) from evento where endereco = :endereco and data = :data",nativeQuery = true)
	Integer buscaEnderecoData(@Param("endereco") String endereco, @Param("data") Date data);

}
