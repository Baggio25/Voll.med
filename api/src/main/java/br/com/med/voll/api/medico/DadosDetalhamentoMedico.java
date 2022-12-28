package br.com.med.voll.api.medico;

import br.com.med.voll.api.endereco.Endereco;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Boolean ativo,
        Especialidade especialidade,
        Endereco endereco
) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getAtivo(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }

}
