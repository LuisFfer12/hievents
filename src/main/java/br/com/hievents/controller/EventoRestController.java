package br.com.hievents.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hievents.dto.evento.EventoDTO;
import br.com.hievents.dto.evento.EventoResponseDTO;
import br.com.hievents.service.EventoService;

@RestController
@RequestMapping("/eventos")
@SuppressWarnings("rawtypes")
public class EventoRestController {
	
	@Autowired
	private EventoService eventoService;
	
	@PostMapping
	public ResponseEntity createAnuncio(@RequestBody EventoDTO requestDTO) {
		EventoResponseDTO response = eventoService.createEvento(requestDTO);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{eventoId}")
	public ResponseEntity getEvento(@PathVariable("eventoId") Integer eventoId) {
		EventoResponseDTO response = eventoService.getEvento(eventoId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity getAllEventos() {
		List<EventoResponseDTO> response = eventoService.getAllEventos();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{eventoId}")
	public ResponseEntity editEventos(@PathVariable("eventoId") Integer eventoId, @RequestBody EventoDTO requestDTO) {
		EventoResponseDTO response = eventoService.editEvento(eventoId, requestDTO);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{eventoId}")
	public void deleteEvento(@PathVariable("eventoId")Integer eventoId) {
		eventoService.deleteEvento(eventoId);
	}
	
	
	

}
