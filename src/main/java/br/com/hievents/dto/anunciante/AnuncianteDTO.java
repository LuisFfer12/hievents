package br.com.hievents.dto.anunciante;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnuncianteDTO {

	private Integer id;
	private String nome;
	private String telefone;
	private String email;
	private String senha;
	private String user;
	
}
