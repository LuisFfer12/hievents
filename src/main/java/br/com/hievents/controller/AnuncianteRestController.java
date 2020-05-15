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

import br.com.hievents.dto.anunciante.AnuncianteDTO;
import br.com.hievents.dto.anunciante.AnuncianteResponseDTO;
import br.com.hievents.service.AnuncianteService;

@RestController
@RequestMapping("/anunciante")
@SuppressWarnings("rawtypes")
public class AnuncianteRestController {

	@Autowired
	AnuncianteService anuncianteService;
	
	
//	@PostMapping
//	public ResponseEntity createAnunciante(@RequestBody AnuncianteDTO requestDTO) {
//		AnuncianteResponseDTO response = anuncianteService.createAnunciante(requestDTO);
//		return ResponseEntity.ok(response);
//	}
	
	@PutMapping("/{anuncianteId}")
	public ResponseEntity editAnunciante(@PathVariable("anuncianteId") Integer anuncianteId, @RequestBody AnuncianteDTO requestDTO) {
		AnuncianteResponseDTO response = anuncianteService.editAnunciante(anuncianteId, requestDTO);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{anuncianteId}")
	public ResponseEntity getAnunciante(@PathVariable("anuncianteId") Integer anuncianteId) {
		AnuncianteResponseDTO response = anuncianteService.getAnunciante(anuncianteId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity getAllAnunciantes() {
		List<AnuncianteResponseDTO> response = anuncianteService.getAllAnunciantes();
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{anuncianteId}")
	public void deleteAnunciante(@PathVariable("anuncianteId") Integer anuncianteId) {
		anuncianteService.deleteAnunciante(anuncianteId);
	}
	
	
}
