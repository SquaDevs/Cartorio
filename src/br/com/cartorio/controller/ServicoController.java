package br.com.cartorio.controller;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cartorio.entity.Servico;
import br.com.cartorio.service.ServicoService;

@Transactional
@Controller
public class ServicoController {
	
	private ServicoService servicoService;
	
	@Autowired
	public ServicoController(ServicoService servicoService) {
		this.servicoService = servicoService;
	}
	
	@RequestMapping("/inserir_servico")
	public String inserirServico(Servico servico, Model model) {
		try {
			model.addAttribute("servico", servicoService.inserirServico(servico));
			return "index";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

//	@RequestMapping("deletar_servico")
//	public String deletarServico(Servico servico, Model model) {
//		try {
//			servicoService.deletarServico(servico);
//			return "index";
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "Erro";
//		}
//	}

//	@RequestMapping("listar_servico")
//	public String listarServico(int idServico, Model model) {
//		try {
//			model.addAttribute("servico", servicoService.listarServico(idServico));
//			return "";
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "Erro";
//		}
//	}

	@RequestMapping("/listar_servicos")
	public String listarServicos(Model model) {
		try {
			model.addAttribute("servicos", servicoService.listarServicos());
			return "ListServico";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/carregar_formulario_servico")
	public String carregarFormulario() {
		return "FormServico";
	}

}
