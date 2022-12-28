package br.com.med.voll.api.controller;

import br.com.med.voll.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(
			@PathVariable(value = "id") Long id) {
		var medico = medicoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(
			@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico,
			UriComponentsBuilder uriComponentsBuilder) {
		var medico = new Medico(dadosCadastroMedico);
		medicoRepository.save(medico);

		var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico,
						  @PathVariable(value = "id") Long id) {
		var medico = medicoRepository.getReferenceById(id);
		medico.atualizarInformacoes(dadosAtualizacaoMedico);

		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(
			@PathVariable(value = "id") Long id) {
		medicoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/inativar/{id}")
	@Transactional
	public ResponseEntity inativar(
			@PathVariable(value = "id") Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.inativar();
		return ResponseEntity.noContent().build();
	}

}
