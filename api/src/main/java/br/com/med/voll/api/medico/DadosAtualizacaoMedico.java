package br.com.med.voll.api.medico;

import br.com.med.voll.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(

        String nome,
        String telefone,
        Boolean ativo,
        DadosEndereco dadosEndereco
) {
}
