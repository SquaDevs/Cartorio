package br.com.cartorio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cartorio.entity.Atendimento;
import br.com.cartorio.entity.Senha;
import br.com.cartorio.entity.SubServico;
import br.com.cartorio.service.AtendimentoService;
import br.com.cartorio.service.SenhaService;
import br.com.cartorio.service.ServicoService;
import br.com.cartorio.service.SubServicoService;

@Transactional
@Controller
public class AtendimentoController {
	private ServicoService servicoService;
	private SenhaService senhaService;
	private SubServicoService subServicoService;
	private AtendimentoService atendimentoService;
	
	@Autowired
	public AtendimentoController(SenhaService senhaService, SubServicoService subServicoService,
			AtendimentoService atendimentoService,ServicoService servicoService) {
		this.senhaService = senhaService;
		this.subServicoService = subServicoService;
		this.atendimentoService = atendimentoService;
		this.servicoService = servicoService;
	}
	
	@RequestMapping("/listar_Atendimento")
	public String listarAtendimento(Model model) {
		try {
			model.addAttribute("subservicos",subServicoService.listarSubServicos());
			
			return "Atendimento";
		} catch (Exception e) {
			e.printStackTrace();
			return  "Erro";
		}
	}
	
	@RequestMapping("/listar_atendimento_senha")
	public String listarSenhaAtendimentos(@ModelAttribute SubServico subServico, Model model) {
		try {
			SubServico subServicoResgatado = subServicoService.listarSubServico(subServico.getId());
			model.addAttribute("subServico", subServicoResgatado);
			
			List<Senha> senhasParaAtender = null;
			List<Senha> senhasEmAtendimento = null;
			
			if(subServicoResgatado.getOrdem() == 1) {
				senhasParaAtender = senhaService.listarSenhasByPrimeiroSubServico(subServicoResgatado);
			}else {
				senhasParaAtender = senhaService.listarSenhasBySubServicoParaAtender(subServicoResgatado);
			}
			senhasEmAtendimento = senhaService.listarSenhasBySubServicoEmAtendimento(subServicoResgatado);
			
			model.addAttribute("senhasParaAtender", senhasParaAtender);
			model.addAttribute("senhasEmAtendimento", senhasEmAtendimento);
			
			return "ListAtendimento";
		} catch (Exception e) {
			e.printStackTrace();
			return  "Erro";
		}
	}
	
	// Usei @RequestParam porque com @ModelAttribute fica dando conflito entre Senha e SubServico
	// Ja que os dois tem o atributo "id", ai o jsp ficava repetindo o mesmo id para os dois,
	// Tentei usar "senha.id" e "subServico.id" no "name" da tag html mas mesmo assim não funciona.
	// Depois que for feita a entrega de hoje mudar os nomes dos atributos nas classes 
	// Por enquanto esta funcionando normal assim. 
	@RequestMapping("/atender_senha")
	public String atenderSenha(@RequestParam("idSenha") int idSenha, @RequestParam("idSubServico") int idSubServico, Model model) {
		try {
			
			Senha senhaResgatada = senhaService.listarSenha(idSenha);
			SubServico subServicoResgatado = subServicoService.listarSubServico(idSubServico);
			senhaService.atenderSenha(senhaResgatada);
			model.addAttribute("senha", senhaResgatada);
			model.addAttribute("subServico", subServicoResgatado);
			
			Atendimento atendimento = new Atendimento();
			atendimento.setSenha(senhaResgatada);
			atendimento.setSubServico(subServicoResgatado);
			
			atendimentoService.inserirAtendimento(atendimento);
			model.addAttribute("atendimento", atendimento);
			
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return  "Erro";
		}
	}
	
	@RequestMapping("/finalizar_senha")
	public String finalizarSenha(@RequestParam("idSenha") int idSenha, @RequestParam("idSubServico") int idSubServico, Model model) {
		try {
			
			Senha senhaResgatada = senhaService.listarSenha(idSenha);
			SubServico subServicoResgatado = subServicoService.listarSubServico(idSubServico);
			int maxOrdem = subServicoService.maxOrdem(subServicoResgatado.getServico());
			model.addAttribute("senha", senhaResgatada);
			model.addAttribute("subServico", subServicoResgatado);
			
			if(maxOrdem == subServicoResgatado.getOrdem()) {
				senhaService.finalizarSenha(senhaResgatada);
			}
			
			Atendimento atendimento = atendimentoService.listarAtendimentosBySubServicoAndSenha(subServicoResgatado, senhaResgatada);
			atendimentoService.fecharAtendimento(atendimento);

			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return  "Erro";
		}
	}
}
