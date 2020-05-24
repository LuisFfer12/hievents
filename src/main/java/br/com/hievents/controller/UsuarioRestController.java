package br.com.hievents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hievents.dto.anunciante.AnuncianteDTO;
import br.com.hievents.dto.anunciante.RecoverPasswordDTO;
import br.com.hievents.dto.anunciante.ResetPasswordDTO;
import br.com.hievents.exception.WrongEmailOrPasswordException;
import br.com.hievents.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	public AnuncianteDTO salvarCadastro(@RequestBody AnuncianteDTO anuncianteDTO) {
		return usuarioServiceImpl.salvar(anuncianteDTO);
	}
	
	@PostMapping("/login")
    public UserDetails login(@RequestBody AnuncianteDTO request) {
		UserDetails user = usuarioServiceImpl.loadUserByUsername(request.getEmail());
		
		boolean match = passwordEncoder.matches(request.getSenha(), user.getPassword());
		
		if(!match) {
			throw new WrongEmailOrPasswordException();
		} else {
			return user;
		}
		
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
