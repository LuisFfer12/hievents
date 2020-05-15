package br.com.hievents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hievents.dto.anunciante.AnuncianteDTO;
import br.com.hievents.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@PostMapping
	public AnuncianteDTO salvarCadastro(@RequestBody AnuncianteDTO anuncianteDTO) {
		return usuarioServiceImpl.salvar(anuncianteDTO);
	}

}
