package br.com.med.voll.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.med.voll.api.medico.DadosCadastroMedico;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

	
	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroMedico dadosCadastroMedico ) {
		System.out.println(dadosCadastroMedico);
	}
	
	
}
