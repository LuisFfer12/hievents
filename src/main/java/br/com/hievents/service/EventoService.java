package br.com.hievents.service;

import org.springframework.stereotype.Service;

import br.com.hievents.dto.evento.EventoDTO;
import br.com.hievents.dto.evento.EventoResponseDTO;

@Service
public interface EventoService {

	EventoResponseDTO createEvento(EventoDTO requestDTO);
}
