package br.com.hievents.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hievents.dto.evento.EventoDTO;
import br.com.hievents.dto.evento.EventoResponseDTO;
import br.com.hievents.entity.evento.Evento;
import br.com.hievents.repository.evento.EventoRepository;
import br.com.hievents.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public EventoResponseDTO createEvento(EventoDTO requestDTO) {
		ModelMapper mapper = new ModelMapper();
		Evento even = mapper.map(requestDTO, Evento.class);
		Evento evento = eventoRepository.save(even);
		EventoResponseDTO resp = mapper.map(evento, EventoResponseDTO.class);
		return resp;
		
	}

}
