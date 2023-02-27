package br.com.med.voll.api.controller;

import br.com.med.voll.api.domain.consulta.AgendaConsultaService;
import br.com.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import br.com.med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultaService agendaConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        agendaConsultaService.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaConsultaService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
