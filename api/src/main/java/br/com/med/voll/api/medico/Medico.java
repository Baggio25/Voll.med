package br.com.med.voll.api.medico;

import br.com.med.voll.api.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome; 
	private String email;
	private String crm; 
	
	@Enumerated(EnumType.STRING)
	Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;
	
	public Medico(DadosCadastroMedico dadosCadastroMedico) {
		this.nome = dadosCadastroMedico.nome();
		this.email = dadosCadastroMedico.email();
		this.crm = dadosCadastroMedico.crm();
		this.especialidade = dadosCadastroMedico.especialidade();
		this.endereco = new Endereco(dadosCadastroMedico.endereco());
	}

}
