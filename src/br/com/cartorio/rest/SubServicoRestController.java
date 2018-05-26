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
@RequestMapping("rest/subServico")
public class SubServicoRestController {
	
	private final ServicoService servicoService;
	private final SubServicoService subservicoServive;
	
	@Autowired
	public SubServicoRestController(ServicoService servicoService,SubServicoService subservicoServive) {
		this.servicoService = servicoService;
		this.subservicoServive = subservicoServive;
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SubServico>> listarServicos() {
		List<SubServico> subServicos = null;
		
		try {
			subServicos = subservicoServive.listarSubServicos();
			return new ResponseEntity<List<SubServico>>(subServicos, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<SubServico>>(subServicos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value = "/{id}")
	public ResponseEntity<SubServico> listarServicos(@PathVariable("id") int id) {
		SubServico subServico = null;
		
		try {
			subServico = subservicoServive.listarSubServico(id);
			return new ResponseEntity<SubServico>(subServico, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<SubServico>(subServico, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value = "/servico/{id}")
	public ResponseEntity<List<SubServico>> listarServicosPorServico(@PathVariable("id") int id) {
		List<SubServico> subServicos = null;
		Servico servico = null;
		
		try {
			servico = servicoService.listarServico(id);
			subServicos = subservicoServive.listarSubServicosByServico(servico);
			return new ResponseEntity<List<SubServico>>(subServicos, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<SubServico>>(subServicos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
