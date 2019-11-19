package br.com.hievents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	

}
