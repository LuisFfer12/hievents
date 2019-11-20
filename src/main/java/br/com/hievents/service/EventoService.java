package br.com.hievents.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.hievents.dto.evento.EventoDTO;
import br.com.hievents.dto.evento.EventoResponseDTO;

@Service
public interface EventoService {

	EventoResponseDTO createEvento(EventoDTO requestDTO);
	
	EventoResponseDTO getEvento(Integer eventoId);

	List<EventoResponseDTO> getAllEventos();

	EventoResponseDTO editEvento(Integer eventoId, EventoDTO requestDTO);

	void deleteEvento(Integer eventoId);
}
