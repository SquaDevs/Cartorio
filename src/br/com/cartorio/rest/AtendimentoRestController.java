package br.com.cartorio.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import br.com.cartorio.dto.AtendimentoDTO;
import br.com.cartorio.entity.Atendimento;
import br.com.cartorio.entity.Senha;
import br.com.cartorio.entity.SubServico;
import br.com.cartorio.service.AtendimentoService;
import br.com.cartorio.service.SenhaService;
import br.com.cartorio.service.SubServicoService;

@Transactional
@RestController
@RequestMapping("rest/atendimento")
public class AtendimentoRestController {
	
	private final AtendimentoService atendimentoService;
	private final SubServicoService subServicoService;
	private final SenhaService senhaService;
	
	@Autowired
	public AtendimentoRestController(AtendimentoService atendimentoService, SubServicoService subServicoService, SenhaService senhaService) {
		this.atendimentoService = atendimentoService;
		this.subServicoService = subServicoService;
		this.senhaService = senhaService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
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
	
	@RequestMapping(method=RequestMethod.POST, value="/naoAtendidos")
	public ResponseEntity<List<Senha>> listarAtendimentoNaoAtendidos(@RequestBody SubServico subServico) {
		SubServico subServicoResgatado  = null;
		List<Senha> senhasParaAtender = null;
		
		try {
			subServicoResgatado = subServicoService.listarSubServico(subServico.getId());
			
			if(subServicoResgatado.getOrdem() == 1) {
				senhasParaAtender = senhaService.listarSenhasBySubServicoParaAtenderInicio(subServicoResgatado);
			}else {
				senhasParaAtender = senhaService.listarSenhasBySubServicoParaAtender(subServicoResgatado);
			}
			
			return new ResponseEntity<List<Senha>>(senhasParaAtender, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Senha>>(senhasParaAtender, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/emAtendimento")
	public ResponseEntity<List<Senha>> listarAtendimentoEmAtendimento(@RequestBody SubServico subServico) {
		SubServico subServicoResgatado  = null;
		List<Senha> senhasEmAtendimento = null;
		
		try {
			subServicoResgatado = subServicoService.listarSubServico(subServico.getId());
			
			senhasEmAtendimento = senhaService.listarSenhasBySubServicoEmAtendimento(subServicoResgatado);

			
			return new ResponseEntity<List<Senha>>(senhasEmAtendimento, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Senha>>(senhasEmAtendimento, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/atender")
	public void atenderSenha(@RequestBody AtendimentoDTO atendimentoDTO) {
		SubServico subServicoResgatado  = null;		
		Senha senhaResgatada = null;
		
		try {
			senhaResgatada = senhaService.listarSenha(atendimentoDTO.getSenha().getId());
			subServicoResgatado = subServicoService.listarSubServico(atendimentoDTO.getSubServico().getId());
			senhaService.atenderSenha(senhaResgatada);
			
			Atendimento atendimento = new Atendimento();
			atendimento.setSenha(senhaResgatada);
			atendimento.setSubServico(subServicoResgatado);
			
			atendimentoService.inserirAtendimento(atendimento);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/finalizar")
	public void finalizarSenha(@RequestBody AtendimentoDTO atendimentoDTO) {
		SubServico subServicoResgatado  = null;		
		Senha senhaResgatada = null;
		
		try {
			senhaResgatada = senhaService.listarSenha(atendimentoDTO.getSenha().getId());
			subServicoResgatado = subServicoService.listarSubServico(atendimentoDTO.getSubServico().getId());
			int maxOrdem = subServicoService.maxOrdem(subServicoResgatado.getServico());
			
			if(maxOrdem == subServicoResgatado.getOrdem()) {
				senhaService.finalizarSenha(senhaResgatada);
			}
			
			Atendimento atendimento = atendimentoService.listarAtendimentosBySubServicoAndSenha(subServicoResgatado, senhaResgatada);
			atendimentoService.fecharAtendimento(atendimento);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
