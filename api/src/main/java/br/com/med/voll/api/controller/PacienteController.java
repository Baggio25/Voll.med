package br.com.med.voll.api.controller;

import br.com.med.voll.api.domain.paciente.DadosListagemPaciente;
import br.com.med.voll.api.domain.paciente.Paciente;
import br.com.med.voll.api.domain.paciente.PacienteRepository;
import br.com.med.voll.api.domain.paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> list(
            @PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao){
        var page = pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(
            @PathVariable(value = "id") Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

	@PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente,
            UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(dadosCadastroPaciente);
        pacienteRepository.save(paciente);

        var uri = uriComponentsBuilder.path("/pecientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(
            @RequestBody @Valid DadosAtualizacaoPaciente dadosAtualizacaoPaciente,
            @PathVariable(value = "id") Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.atualizarInformacoes(dadosAtualizacaoPaciente);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(
            @PathVariable(value = "id") Long id) {
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/inativar/{id}")
    @Transactional
    public ResponseEntity inativar(
            @PathVariable(value = "id") Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
        return ResponseEntity.noContent().build();
    }

	
}
