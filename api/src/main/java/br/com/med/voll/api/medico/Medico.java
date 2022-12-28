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

	private String telefone;

	private String crm;

	private Boolean ativo;
	
	@Enumerated(EnumType.STRING)
	Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;
	
	public Medico(DadosCadastroMedico dadosCadastroMedico) {
		this.ativo = true;
		this.nome = dadosCadastroMedico.nome();
		this.email = dadosCadastroMedico.email();
		this.telefone = dadosCadastroMedico.telefone();
		this.crm = dadosCadastroMedico.crm();
		this.especialidade = dadosCadastroMedico.especialidade();
		this.endereco = new Endereco(dadosCadastroMedico.endereco());
	}

	public void atualizarInformacoes(DadosAtualizacaoMedico dadosAtualizacaoMedico) {
		if(dadosAtualizacaoMedico.nome() != null) {
			this.nome = dadosAtualizacaoMedico.nome();
		}
		if(dadosAtualizacaoMedico.telefone() != null) {
			this.telefone = dadosAtualizacaoMedico.telefone();
		}
		if(dadosAtualizacaoMedico.dadosEndereco() != null) {
			this.endereco.atualizarInformacoes(dadosAtualizacaoMedico.dadosEndereco());
		}
		if(dadosAtualizacaoMedico.ativo() != null) {
			this.ativo = dadosAtualizacaoMedico.ativo();
		}
	}

	public void inativar() {
		this.ativo = false;
	}
}
