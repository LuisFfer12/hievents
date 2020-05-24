package br.com.hievents.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.hievents.dto.anunciante.AnuncianteDTO;
import br.com.hievents.dto.anunciante.RecoverPasswordDTO;
import br.com.hievents.dto.anunciante.ResetPasswordDTO;
import br.com.hievents.entity.anunciante.Anunciante;
import br.com.hievents.exception.AnuncianteNotFoundException;
import br.com.hievents.exception.EmailAlreadyExistsException;
import br.com.hievents.exception.WrongEmailOrPasswordException;
import br.com.hievents.repository.anunciante.AnuncianteRepository;
import br.com.hievents.utils.EmailUtils;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailUtils emailUtils;
	
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

	public void recover(RecoverPasswordDTO requestDTO) {
		Optional<Anunciante> userOptional = anuncianteRepository.findByEmail(requestDTO.getEmail());
		if(!userOptional.isPresent()) {
			return;
		}

		Anunciante user = userOptional.get();

		String token = RandomStringUtils.random(16, true, true);
		user.setRecoveryToken(token);
		anuncianteRepository.save(user);

		String emailBody = "<a href='http://localhost:3000/recover/{{token}}'>Clique aqui para recuperar sua senha</a>";
		Map<String, String> values = new HashMap<>();
		values.put("token", token);

		emailUtils.SendEmail(requestDTO.getEmail(), "Recuperação de Senha", emailBody, values);
		
	}

	public Object reset(ResetPasswordDTO requestDTO) {
		List<Anunciante> users = anuncianteRepository.findAllByRecoveryToken(requestDTO.getToken());
		if (users.size() == 0) {
			throw new AnuncianteNotFoundException();
		}
		if (users.size() > 1) {
			for(Anunciante user : users) {
				user.setRecoveryToken(null);
			}
			anuncianteRepository.saveAll(users);
			throw new AnuncianteNotFoundException();
		}

		Anunciante user = users.get(0);
		user.setRecoveryToken(null);
		user.setSenha(passwordEncoder.encode(requestDTO.getSenha()));
		anuncianteRepository.save(user);

		RecoverPasswordDTO resetPasswordResponseDTO = new RecoverPasswordDTO();
		resetPasswordResponseDTO.setEmail(user.getEmail());

		return resetPasswordResponseDTO;
	}
	
}
