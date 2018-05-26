package br.com.cartorio.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartorio.entity.Servico;
import br.com.cartorio.entity.SubServico;
import br.com.cartorio.service.ServicoService;
import br.com.cartorio.service.SubServicoService;

@RestController
@RequestMapping("rest/servico")
public class ServicoRestController {
	
	private final ServicoService servicoService;
	private final SubServicoService subservicoServive;
	
	@Autowired
	public ServicoRestController(ServicoService servicoService,SubServicoService subservicoServive) {
		this.servicoService = servicoService;
		this.subservicoServive = subservicoServive;
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Servico>> listarServicos() {
		List<Servico> servicos = null;
		
		try {
			servicos = servicoService.listarServicos();
			return new ResponseEntity<List<Servico>>(servicos, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Servico>>(servicos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET ,value = "/{id}")
	public ResponseEntity<Servico> listarServicos(@PathVariable("id") int id) {

		Servico servico = null;
		
		try {
			servico = servicoService.listarServico(id);
			return new ResponseEntity<Servico>(servico, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Servico>(servico, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}

