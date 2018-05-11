package br.com.cartorio.rest;

import java.io.IOException;
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
	@RequestMapping(method=RequestMethod.POST ,produces = "application/json", consumes = "application/json")
	public ResponseEntity<SenhaRest> inserirSenha(@RequestBody Senha senha) {
		System.out.println( senha);
		SenhaRest  rest =   new SenhaRest();
		try {	
			senhaService.inserirSenha(senha);
			rest.setSenha(senha);
			rest.setPrevisaoFim(senhaService.previsaoTermino(senha.getServico()));
			rest.setPrevisaoIni(senhaService.previsaoInicio(senha.getServico()));
			
				System.out.println(rest.getPrevisaoFim());
			
			return new ResponseEntity<SenhaRest>(rest, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<SenhaRest>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
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
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}" )
	public ResponseEntity<SenhaRest> listarSenha(@PathVariable("id") int id) {
		Senha senha = null;
		SenhaRest  rest =   new SenhaRest();
		try {
			senha = senhaService.listarSenha(id);
			rest.setSenha(senha);
			rest.setPrevisaoFim(senhaService.previsaoTermino(senha.getServico()));
			rest.setPrevisaoIni(senhaService.previsaoInicio(senha.getServico()));
			
			
			return new ResponseEntity<SenhaRest>(rest, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<SenhaRest>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value="/painel" )
	public ResponseEntity<List<Senha>> listarSenhasPainel() {
		List<Senha> senhas = null;
		
		try {
			//Refatorar ou refazer o metodo "listarSenhasPainel" caso necessario
			//Ele apenas retorna as 5 primeiras senha não finalizadas
			senhas = senhaService.listarSenhasPainel();
			return new ResponseEntity<List<Senha>>(senhas, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Senha>>(senhas, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}



class SenhaRest {
	private Senha  senha;
	private Double previsaoIni;
	private Double previsaoFim;
	public Senha getSenha() {
		return senha;
	}
	public void setSenha(Senha senha) {
		this.senha = senha;
	}
	public Double getPrevisaoIni() {
		return previsaoIni;
	}
	public void setPrevisaoIni(Double previsaoIni) {
		this.previsaoIni = previsaoIni;
	}
	public Double getPrevisaoFim() {
		return previsaoFim;
	}
	public void setPrevisaoFim(Double previsaoFim) {
		this.previsaoFim = previsaoFim;
	}
	
	
}
	
	
	

