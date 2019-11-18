package br.com.hievents.repository.anunciante;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hievents.entity.anunciante.Anunciante;

@Repository
public interface AnuncianteRepository extends JpaRepository<Anunciante, Integer> {

	boolean existsByEmail(String email);

	Optional<Anunciante> findById(Integer anuncianteId);

	void deleteById(Integer anuncianteId);


}
