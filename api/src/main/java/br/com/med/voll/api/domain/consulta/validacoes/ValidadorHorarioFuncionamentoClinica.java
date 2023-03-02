package br.com.med.voll.api.domain.consulta.validacoes;

import br.com.med.voll.api.domain.ValidacaoException;
import br.com.med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY); //valida se é domingo
        var antesDaAberturaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaClinica || depoisDoEncerramentoClinica) {
            throw new ValidacaoException("Comsulta fora do horário de funcionamento da clínica.");
        }
    }

}
