package br.com.hievents.entity.evento;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hievents.entity.anunciante.Anunciante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evento")
@SuppressWarnings("serial")
public class Evento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date data;
	
	private String horario;

//	@ManyToOne
//	@JoinColumn(name="anunciante_id")
//	private Anunciante anunciante;


}
