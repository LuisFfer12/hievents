package br.com.hievents.dto.evento;

import lombok.Data;

@Data
public class EventoDTO {
	
	private Integer id;
	private String nome;
	private String local;
	private String data;
	private String horario;


}
