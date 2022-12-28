package br.com.med.voll.api.domain.medico;

import br.com.med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(

        String nome,
        String telefone,
        Boolean ativo,
        DadosEndereco dadosEndereco
) {
}
