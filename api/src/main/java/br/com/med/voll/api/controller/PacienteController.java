package br.com.med.voll.api.controller;

import br.com.med.voll.api.paciente.DadosListagemPaciente;
import br.com.med.voll.api.paciente.Paciente;
import br.com.med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import br.com.med.voll.api.paciente.DadosCadastroPaciente;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

	@PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente dadosCadastroPaciente) {
        pacienteRepository.save(new Paciente(dadosCadastroPaciente));
    }

    @GetMapping
    public Page<DadosListagemPaciente> list(
            @PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable paginacao){
        return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
	
}
