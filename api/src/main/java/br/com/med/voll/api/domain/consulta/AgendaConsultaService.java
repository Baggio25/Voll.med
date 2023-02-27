package br.com.med.voll.api.domain.consulta;

import br.com.med.voll.api.domain.ValidacaoException;
import br.com.med.voll.api.domain.medico.Medico;
import br.com.med.voll.api.domain.medico.MedicoRepository;
import br.com.med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {
        validaIntegridadeDosDadosAgendamento(dados);

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        validaIntegridadeDosDadosCancelamento(dados);

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private void validaIntegridadeDosDadosCancelamento(DadosCancelamentoConsulta dados) {
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe.");
        }
    }

    private void validaIntegridadeDosDadosAgendamento(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe.");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe.");
        }
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idPaciente());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido");
        }

        var medicoDisponivel = medicoRepository
                .escolherMedidoAleatorioLivreNaData(dados.especialidade(), dados.data());
        return medicoDisponivel;
    }

}
