package br.com.med.voll.api.domain.consulta.validacoes;

import br.com.med.voll.api.domain.ValidacaoException;
import br.com.med.voll.api.domain.consulta.ConsultaRepository;
import br.com.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoMesmoDia implements  ValidadorAgendamentoConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiConsultaNoMesmoDia =
                consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if(pacientePossuiConsultaNoMesmoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia.");
        }

    }

}
