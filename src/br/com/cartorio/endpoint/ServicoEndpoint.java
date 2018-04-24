package br.com.cartorio.endpoint;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartorio.entity.Servico;
import br.com.cartorio.service.ServicoService;

@RestController
@RequestMapping("api/servico")
public class ServicoEndpoint {
	
	private final ServicoService servicoService;
	
	@Autowired
	public ServicoEndpoint(ServicoService servicoService) {
		this.servicoService = servicoService;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/servicos" )
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
}

