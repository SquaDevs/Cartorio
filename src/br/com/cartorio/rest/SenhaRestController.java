package br.com.cartorio.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartorio.dto.SenhaDTO;
import br.com.cartorio.entity.Senha;
import br.com.cartorio.service.SenhaService;

@RestController
@RequestMapping("rest/senha")
public class SenhaRestController {

	private final SenhaService senhaService;

	@Autowired
	public SenhaRestController(SenhaService senhaService) {
		this.senhaService = senhaService;
	}

	@CrossOrigin
	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "/inserir")
	public ResponseEntity<SenhaDTO> inserirSenha(@RequestBody Senha senha) {
		SenhaDTO rest = new SenhaDTO();
		try {
			senhaService.inserirSenha(senha);
			rest.setSenha(senha);
			
			Date previsaoIni = SenhaDTO.calcularTempoMinutos(senha.getData_inicio(), senhaService.previsaoInicio(senha.getServico()));
			Date previsaoFim = SenhaDTO.calcularTempoMinutos(senha.getData_inicio(), senhaService.previsaoTermino(senha.getServico()));
			
			rest.setPrevisaoIni(previsaoIni);
			rest.setPrevisaoFim(previsaoFim);

			return new ResponseEntity<SenhaDTO>(rest, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<SenhaDTO>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Senha>> listarSenhas() {
		List<Senha> senhas = null;

		try {
			senhas = senhaService.listarSenhas();
			return new ResponseEntity<List<Senha>>(senhas, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Senha>>(senhas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<SenhaDTO> listarSenha(@PathVariable("id") int id) {
		Senha senha = null;
		SenhaDTO rest = new SenhaDTO();
		try {
			senha = senhaService.listarSenha(id);
			rest.setSenha(senha);
			
			Date previsaoIni = SenhaDTO.calcularTempoMinutos(senha.getData_inicio(), senhaService.previsaoInicio(senha.getServico()));
			Date previsaoFim = SenhaDTO.calcularTempoMinutos(senha.getData_inicio(), senhaService.previsaoTermino(senha.getServico()));
			
			rest.setPrevisaoIni(previsaoIni);
			rest.setPrevisaoFim(previsaoFim);

			return new ResponseEntity<SenhaDTO>(rest, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<SenhaDTO>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/painel")
	public ResponseEntity<List<Senha>> listarSenhasPainel() {
		List<Senha> senhas = null;

		try {
			senhas = senhaService.listarSenhasPainel();
			return new ResponseEntity<List<Senha>>(senhas, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Senha>>(senhas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/chamar/{id}")
	public ResponseEntity<Senha> chamarSenha(@PathVariable("id") int id) {
		Senha senhaChamada = null;

		try {
			senhaChamada = senhaService.listarSenhaPainel(id);
			
			return new ResponseEntity<Senha>(senhaChamada, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Senha>(senhaChamada, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
