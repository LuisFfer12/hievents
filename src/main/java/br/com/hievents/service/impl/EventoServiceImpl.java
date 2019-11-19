package br.com.hievents.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hievents.dto.evento.EventoDTO;
import br.com.hievents.dto.evento.EventoResponseDTO;
import br.com.hievents.entity.evento.Evento;
import br.com.hievents.exception.EventoAlreadyExistsException;
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
		Integer existeEvento = eventoRepository.buscaEnderecoData(requestDTO.getEndereco(), requestDTO.getData());
		if(existeEvento >0) {
			throw new EventoAlreadyExistsException();
		}
		Evento eventoSalvo = eventoRepository.save(even);
		EventoResponseDTO response = mapper.map(eventoSalvo, EventoResponseDTO.class);
		return response;
	}
	
	

}
