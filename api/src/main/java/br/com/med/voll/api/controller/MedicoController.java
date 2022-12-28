package br.com.med.voll.api.controller;

import br.com.med.voll.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	public void cadastrar(
			@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico ) {
		medicoRepository.save(new Medico(dadosCadastroMedico));
	}

	@GetMapping
	public Page<DadosListagemMedico> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
	}

	@PutMapping("/{id}")
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dadosAtualizacaoMedico,
						  @PathVariable(value = "id") Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.atualizarInformacoes(dadosAtualizacaoMedico);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable(value = "id") Long id) {
		medicoRepository.deleteById(id);
	}

	@PutMapping("/inativar/{id}")
	@Transactional
	public void inativar(@PathVariable(value = "id") Long id) {
		Medico medico = medicoRepository.getReferenceById(id);
		medico.inativar();
	}

}
