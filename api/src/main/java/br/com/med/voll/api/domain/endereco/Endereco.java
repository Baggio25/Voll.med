package br.com.med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	private String logradouro;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private String complemento; 
	private String numero;
	
	public Endereco(DadosEndereco endereco) {
		this.logradouro = endereco.logradouro();
		this.bairro = endereco.bairro();
		this.cep = endereco.cep();
		this.cidade = endereco.cidade();
		this.uf = endereco.uf();
		this.complemento = endereco.complemento();
		this.numero = endereco.numero();
	}

	public void atualizarInformacoes(DadosEndereco dadosEndereco) {
		if(dadosEndereco.logradouro() != null) {
			this.logradouro = dadosEndereco.logradouro();
		}
		if(dadosEndereco.bairro() != null) {
			this.bairro = dadosEndereco.bairro();
		}
		if(dadosEndereco.cep() != null) {
			this.cep = dadosEndereco.cep();
		}
		if(dadosEndereco.cidade() != null) {
			this.cidade = dadosEndereco.cidade();
		}
		if(dadosEndereco.uf() != null) {
			this.uf = dadosEndereco.uf();
		}
		if(dadosEndereco.complemento() != null) {
			this.complemento = dadosEndereco.complemento();
		}
		if(dadosEndereco.numero() != null) {
			this.numero = dadosEndereco.numero();
		}

	}
}
