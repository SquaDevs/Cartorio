package br.com.cartorio.controller;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.cartorio.entity.SubServico;
import br.com.cartorio.service.ServicoService;
import br.com.cartorio.service.SubServicoService;

@Transactional
@Controller
public class SubServicoController {

	private SubServicoService subServicoService;
	private ServicoService servicoService;

	@Autowired
	public SubServicoController(SubServicoService subServicoService, ServicoService servicoService) {
		this.subServicoService = subServicoService;
		this.servicoService = servicoService;
	}

	@RequestMapping("/index")
	public String inicio() {
		return "index";
	}

	@RequestMapping("/inserir_subservico")
	public String inserirServico(@ModelAttribute SubServico subServico,  Model model) {
		try {
			model.addAttribute("servicos", subServicoService.inserirSubServico(subServico,subServico.getServico()));
			return "index";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

//	@RequestMapping("deletar_subservico")
//	public String deletarServico(SubServico subServico, Model model) {
//		try {
//			subServicoService.deletarServico(subServico);
//			return "index";
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "Erro";
//		}
//	}
//
	@RequestMapping("/carregar_servico_subservico")
	public String listarSubServico(Model model) {
		try {
			SubServico subServico = new SubServico();
			model.addAttribute("servicos", servicoService.listarServicos());
			model.addAttribute("subServico", subServico);
			return "FormSubServico";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_subservicos")
	public String listarSubServicos( Model model) {
		try {
			model.addAttribute("subServicos", subServicoService.listarSubServicos());
			return "ListSubServico";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

}
