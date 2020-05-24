package br.com.hievents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hievents.dto.anunciante.AnuncianteDTO;
import br.com.hievents.dto.anunciante.RecoverPasswordDTO;
import br.com.hievents.dto.anunciante.ResetPasswordDTO;
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
	
	@PostMapping("/login")
    public UserDetails login(@RequestBody AnuncianteDTO request) {
        return usuarioServiceImpl.loadUserByUsername(request.getEmail(), request.getSenha());
    }
	
	@PostMapping("/recover")
	public void recover(@RequestBody RecoverPasswordDTO requestDTO) {
		usuarioServiceImpl.recover(requestDTO);
	}

	@PostMapping("/reset")
	public ResponseEntity recoverWithToken(@RequestBody ResetPasswordDTO requestDTO) {
		return ResponseEntity.ok(usuarioServiceImpl.reset(requestDTO));
	}

}
