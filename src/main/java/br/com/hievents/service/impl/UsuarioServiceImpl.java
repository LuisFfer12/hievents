package br.com.hievents.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hievents.dto.anunciante.AnuncianteDTO;
import br.com.hievents.entity.anunciante.Anunciante;
import br.com.hievents.exception.EmailAlreadyExistsException;
import br.com.hievents.exception.WrongEmailOrPasswordException;
import br.com.hievents.repository.anunciante.AnuncianteRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AnuncianteRepository anuncianteRepository;

	public AnuncianteDTO salvar(AnuncianteDTO anuncianteDto) {
		ModelMapper mapper = new ModelMapper();
		
		String senhaCriptografada = passwordEncoder.encode(anuncianteDto.getSenha());
		
		Anunciante anunciante = mapper.map(anuncianteDto, Anunciante.class);
		anunciante.setUser("USER");
		anunciante.setSenha(senhaCriptografada);

		Boolean existsByEmail = anuncianteRepository.existsByEmail(anuncianteDto.getEmail());
		if (existsByEmail == true) {
			throw new EmailAlreadyExistsException();
		}

		Anunciante anuncianteSalvo = anuncianteRepository.save(anunciante);
		AnuncianteDTO response = mapper.map(anuncianteSalvo, AnuncianteDTO.class);
		return response;

	}

	public UserDetails loadUserByUsername(String email, String senha) throws UsernameNotFoundException {
		Anunciante anunciante = anuncianteRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));
		
		boolean match = passwordEncoder.matches(senha, anunciante.getSenha());
		
		if(!match) {
			throw new WrongEmailOrPasswordException();
		}
				 
		return User
				.builder()
				.username(anunciante.getEmail())
				.password(anunciante.getSenha())
				.roles(anunciante.getUser())
				.build();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
