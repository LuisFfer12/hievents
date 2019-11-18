package br.com.hievents.dto.anunciante;

import lombok.Data;

@Data
public class AnuncianteResponseDTO {

	private Integer id;
	private String nome;
	private String telefone;
	private String email;
}
