package br.com.med.voll.api.domain.consulta.validacoes;

import br.com.med.voll.api.domain.ValidacaoException;
import br.com.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements  ValidadorAgendamentoConsultas{

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com um paciente inativo.");
        }
    }



}
