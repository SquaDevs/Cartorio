package br.com.cartorio.rest;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	@RequestMapping(method=RequestMethod.POST ,produces = "application/json", consumes = "application/json")

	public ResponseEntity<SenhaDTO> inserirSenha(@RequestBody Senha senha) {
		System.out.println( senha);
		SenhaDTO  rest =   new SenhaDTO();
		try {	
			senhaService.inserirSenha(senha);
			rest.setSenha(senha);
			Date previsaoFim =SenhaDTO.calcularTempoMinutos(senha.getData_fim(), senhaService.previsaoTermino(senha.getServico()));
			rest.setPrevisaoFim(previsaoFim);
			Date previsaoIni = SenhaDTO.calcularTempoMinutos(senha.getData_inicio(), senhaService.previsaoInicio(senha.getServico()));
			rest.setPrevisaoIni(previsaoIni);
				System.out.println(rest.getPrevisaoFim());
			
			return new ResponseEntity<SenhaDTO>(rest, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<SenhaDTO>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
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
	public ResponseEntity<SenhaDTO> listarSenha(@PathVariable("id") int id) {
		Senha senha = null;
		SenhaDTO  rest =   new SenhaDTO();
		try {
			senha = senhaService.listarSenha(id);
			rest.setSenha(senha);
			String previsaoFim =SenhaDTO.calcularTempoMinutosStr(senha.getData_fim(), senhaService.previsaoTermino(senha.getServico()));
			rest.setPrevisaoFimStr(previsaoFim);
			String previsaoIni = SenhaDTO.calcularTempoMinutosStr(senha.getData_inicio(), senhaService.previsaoInicio(senha.getServico()));
			rest.setPrevisaoIniStr(previsaoIni);
			
			
			return new ResponseEntity<SenhaDTO>(rest, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<SenhaDTO>(rest, HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value="/chamar" )
	public ResponseEntity<Senha> chamarSenha() {
		List<Senha> senhas = null;
		Senha senhaChamada = null;
		
		try {
			senhas = senhaService.chamarSenhasPainel();
			System.out.println(senhas);
			 
			if(!senhas.isEmpty()) {
				if(senhas.size() > 1 && senhas.get(1).getPreferencial() && !senhas.get(0).getPreferencial()) {
					if(senhas.get(1).getNumero() - senhas.get(0).getNumero() < 3) {
						senhaChamada = senhas.get(1);
					}
				} else if(senhas.size() > 2 && senhas.get(2).getPreferencial() && (!senhas.get(0).getPreferencial() && !senhas.get(1).getPreferencial())) {
					if(senhas.get(2).getNumero() - senhas.get(0).getNumero() < 3) {
						senhaChamada = senhas.get(2);
					}
				} if(senhaChamada == null) {
					senhaChamada = senhas.get(0);
				}
			}
			
			return new ResponseEntity<Senha>(senhaChamada, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Senha>(senhaChamada, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
	
	

