package br.com.hievents.dto.evento;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hievents.dto.anunciante.AnuncianteResponseDTO;
import lombok.Data;

@Data
public class EventoResponseDTO {
	
	private Integer id;
	private String nome;
	private String endereco;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date data;
	private String horario;
	private String banner;
	private String categoria;

	private AnuncianteResponseDTO anunciante;


}
