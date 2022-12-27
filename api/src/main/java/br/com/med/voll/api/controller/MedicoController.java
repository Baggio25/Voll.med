package br.com.med.voll.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.med.voll.api.medico.DadosCadastroMedico;
import br.com.med.voll.api.medico.Medico;
import br.com.med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;

	
	@PostMapping
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico ) {
		medicoRepository.save(new Medico(dadosCadastroMedico));
	}
	
	
}
