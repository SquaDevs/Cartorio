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
			Date previsaoFim =SenhaRest.calcularTempoMinutos(senha.getData_fim(), senhaService.previsaoTermino(senha.getServico()));
			rest.setPrevisaoFim(previsaoFim);
			Date previsaoIni = SenhaRest.calcularTempoMinutos(senha.getData_inicio(), senhaService.previsaoInicio(senha.getServico()));
			rest.setPrevisaoIni(previsaoIni);
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
			String previsaoFim =SenhaRest.calcularTempoMinutosStr(senha.getData_fim(), senhaService.previsaoTermino(senha.getServico()));
			rest.setPrevisaoFimStr(previsaoFim);
			String previsaoIni = SenhaRest.calcularTempoMinutosStr(senha.getData_inicio(), senhaService.previsaoInicio(senha.getServico()));
			rest.setPrevisaoIniStr(previsaoIni);
			
			
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
	private Date previsaoIni;
	private Date previsaoFim;
	private String previsaoIniStr;
	private String previsaoFimStr;
	
	public Senha getSenha() {
		return senha;
	}
	public void setSenha(Senha senha) {
		this.senha = senha;
	}
	
	
	public Date getPrevisaoIni() {
		return previsaoIni;
	}
	public void setPrevisaoIni(Date previsaoIni) {
		this.previsaoIni = previsaoIni;
	}
	public Date getPrevisaoFim() {
		return previsaoFim;
	}
	public void setPrevisaoFim(Date previsaoFim) {
		this.previsaoFim = previsaoFim;
	}
	public String getPrevisaoIniStr() {
		return previsaoIniStr;
	}
	public void setPrevisaoIniStr(String previsaoIniStr) {
		this.previsaoIniStr = previsaoIniStr;
	}
	public String getPrevisaoFimStr() {
		return previsaoFimStr;
	}
	public void setPrevisaoFimStr(String previsaoFimStr) {
		this.previsaoFimStr = previsaoFimStr;
	}
	public static String	calcularTempoMinutosStr(Date data, double minutosd) {
		int minutos = (int) minutosd;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MINUTE,minutos);
		Date novaData = calendar.getTime();
		SimpleDateFormat  dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dataFormat.format(novaData);
	}
	public static Date	calcularTempoMinutos(Date data, double minutosd) {
		int minutos = (int) minutosd;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MINUTE,minutos);
		Date novaData = calendar.getTime();
		return novaData;
	}
	
	
}
	
	
	

