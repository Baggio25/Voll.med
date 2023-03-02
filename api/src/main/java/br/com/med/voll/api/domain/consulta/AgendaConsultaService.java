package br.com.med.voll.api.domain.consulta;

import br.com.med.voll.api.domain.ValidacaoException;
import br.com.med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsultas;
import br.com.med.voll.api.domain.medico.Medico;
import br.com.med.voll.api.domain.medico.MedicoRepository;
import br.com.med.voll.api.domain.paciente.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsultas> validadores;

    public void agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe.");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe.");
        }

        validadores.forEach(v -> v.validar(dados)); //chama cada classe de validação com o metodo validar, passando os dados

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe.");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
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
