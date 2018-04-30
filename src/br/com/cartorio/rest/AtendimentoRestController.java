package br.com.cartorio.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartorio.entity.Atendimento;
import br.com.cartorio.service.AtendimentoService;

@RestController
@RequestMapping("api/atendimento")
public class AtendimentoRestController {
	
	private final AtendimentoService atendimentoService;
	
	@Autowired
	public AtendimentoRestController(AtendimentoService atendimentoService) {
		this.atendimentoService = atendimentoService;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/all")
	public ResponseEntity<List<Atendimento>> listarAtendimentos() {
		List<Atendimento> atendimentos = null;
		
		try {
			atendimentos = atendimentoService.listarAtendimentos();
			return new ResponseEntity<List<Atendimento>>(atendimentos, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Atendimento>>(atendimentos, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Atendimento> listarAtendimento(@PathVariable("id") int id) {
		Atendimento atendimento = null;
		
		try {
			atendimento = atendimentoService.listarAtendimento(id);
			return new ResponseEntity<Atendimento>(atendimento, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Atendimento>(atendimento, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
