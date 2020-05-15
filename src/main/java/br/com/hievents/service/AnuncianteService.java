package br.com.hievents.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.hievents.dto.anunciante.AnuncianteDTO;
import br.com.hievents.dto.anunciante.AnuncianteResponseDTO;

@Service
public interface AnuncianteService {

	//AnuncianteResponseDTO createAnunciante(AnuncianteDTO requestDTO);

	AnuncianteResponseDTO editAnunciante(Integer anuncianteId, AnuncianteDTO requestDTO);

	AnuncianteResponseDTO getAnunciante(Integer anuncianteId);

	List<AnuncianteResponseDTO> getAllAnunciantes();

	void deleteAnunciante(Integer anuncianteId);


}
