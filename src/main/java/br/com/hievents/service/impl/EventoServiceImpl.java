package br.com.hievents.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import br.com.hievents.dto.evento.EventoDTO;
import br.com.hievents.dto.evento.EventoResponseDTO;
import br.com.hievents.entity.evento.Evento;
import br.com.hievents.exception.EventoAlreadyExistsException;
import br.com.hievents.exception.EventoNotFoundException;
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
		if(existeEvento > 0) {
			throw new EventoAlreadyExistsException();
		}
		Evento eventoSalvo = eventoRepository.save(even);
		EventoResponseDTO response = mapper.map(eventoSalvo, EventoResponseDTO.class);
		return response;
	}
	
	@Override
	public EventoResponseDTO getEvento(Integer eventoId) {
		ModelMapper mapper = new ModelMapper();
		Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
		if (!eventoOpt.isPresent()) {
			throw new EventoNotFoundException();
		}
		Evento evento =  eventoOpt.get();
		EventoResponseDTO response = mapper.map(evento, EventoResponseDTO.class);
		return response;
	}
	
	@Override
	public List<EventoResponseDTO> getAllEventos(){
		ModelMapper mapper = new ModelMapper();
		List<Evento> evento = eventoRepository.findAll();
		Type listType = new TypeToken< List<EventoResponseDTO>>() {}.getType();
		List<EventoResponseDTO> response = mapper.map(evento, listType);
		return response;
	}
	
	@Override
	public EventoResponseDTO editEvento(Integer eventoId, EventoDTO requestDTO) {
		ModelMapper mapper = new ModelMapper();
		Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
		if (!eventoOpt.isPresent()) {
			throw new EventoNotFoundException();
		}
		Evento evento = eventoOpt.get();
		if (!StringUtils.equals(requestDTO.getNome(), evento.getNome())) {
			Integer eventoExists = eventoRepository.buscaNome(requestDTO.getNome());
			if (eventoExists > 0) {
				throw new EventoAlreadyExistsException();
			}
		}
		mapper.map(requestDTO, evento);
		Evento eventoSaved = eventoRepository.save(evento);
		EventoResponseDTO response = mapper.map(eventoSaved, EventoResponseDTO.class);
		return response;
	}
	
	@Override
	public void deleteEvento(Integer eventoId) {
		Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
		if (!eventoOpt.isPresent()) {
			throw new EventoNotFoundException();
		} else {
		eventoRepository.deleteById(eventoId);
		}
	}

}
