package br.com.med.voll.api.controller;

import br.com.med.voll.api.medico.DadosListagemMedico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.med.voll.api.medico.DadosCadastroMedico;
import br.com.med.voll.api.medico.Medico;
import br.com.med.voll.api.medico.MedicoRepository;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico ) {
		medicoRepository.save(new Medico(dadosCadastroMedico));
	}

	@GetMapping
	public List<DadosListagemMedico> listar() {
		return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
	}
}
