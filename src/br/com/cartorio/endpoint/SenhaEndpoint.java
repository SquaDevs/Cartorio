package br.com.cartorio.endpoint;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartorio.entity.Senha;
import br.com.cartorio.service.SenhaService;

@RestController
@RequestMapping("api/senha")
public class SenhaEndpoint {
	
	private final SenhaService senhaService;
	
	@Autowired
	public SenhaEndpoint(SenhaService senhaService) {
		this.senhaService = senhaService;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST, value="/salvar" )
	public ResponseEntity<Senha> inserirSenha(@RequestBody Senha senha) {
		try {
			senhaService.inserirSenha(senha);
			return new ResponseEntity<Senha>(senha, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Senha>(senha, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/all" )
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
	public ResponseEntity<Senha> listarSenha(@PathVariable("id") int id) {
		Senha senha = null;
		
		try {
			senha = senhaService.listarSenha(id);
			return new ResponseEntity<Senha>(senha, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Senha>(senha, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
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
