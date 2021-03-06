package br.com.cartorio.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cartorio.dao.SenhaDAO;
import br.com.cartorio.entity.Senha;
import br.com.cartorio.entity.Servico;
import br.com.cartorio.entity.SubServico;

@Service
public class SenhaService {
	SenhaDAO dao;
	ServicoService servicoService;
	
	@Autowired
	public SenhaService(SenhaDAO dao, ServicoService servicoService) {
		this.dao = dao;
		this.servicoService = servicoService;
	}
	
	public int inserirSenha(Senha senha) throws IOException {
		Servico servico  = servicoService.listarServico(senha.getServico().getId());
		senha.setServico(servico);
		int ultimoNumero = ultimoNumeroByServico(servico); 
		senha.setNumero(ultimoNumero + 1);
		
		return dao.inserirSenha(senha);
	}
	
	public void atenderSenha(Senha senha) throws IOException {
		dao.atenderSenha(senha);
	}
	
	public void finalizarSenha(Senha senha) throws IOException {
		dao.finalizarSenha(senha);
	}

	public void deletarSenha(Senha senha) throws IOException {
		dao.deletarSenha(senha);
	}
	
	public Senha listarSenha(int idSenha) throws IOException {
		return dao.listarSenha(idSenha);
	}

	public List<Senha> listarSenhas() throws IOException {
		return dao.listarSenhas();
	}
	
	public List<Senha> listarSenhasPainel() throws IOException {
		return dao.listarSenhasPainel();
	}
	
	public List<Senha> chamarSenhasPainel() throws IOException {
		return dao.chamarSenhasPainel();
	}
	
	public List<Senha> listarSenhasBySubServico(SubServico subServico) throws IOException {
		return dao.listarSenhasBySubServico(subServico);
	}
	
	public List<Senha> listarSenhasBySubServicoParaAtenderInicio(SubServico subServico) throws IOException {
		return dao.listarSenhasBySubServicoParaAtenderInicio(subServico);
	}
	
	public List<Senha> listarSenhasBySubServicoParaAtender(SubServico subServico) throws IOException {
		return dao.listarSenhasBySubServicoParaAtender(subServico);
	}
	
	public List<Senha> listarSenhasBySubServicoEmAtendimento(SubServico subServico) throws IOException {
		return dao.listarSenhasBySubServicoEmAtendimento(subServico);
	}
	
	public int ultimoNumeroByServico(Servico servico) throws IOException{
		return dao.ultimoNumeroByServico(servico);
	}
	
	public Double previsaoInicio(Servico servico) throws IOException{
		return dao.previsaoInicio(servico);
	}
	
	public Double previsaoTermino(Servico servico) throws IOException{
		return dao.previsaoTermino(servico);
	}

	public Senha listarSenhaPainel(int id) throws IOException {
		return dao.listarSenhaPainel(id);
	}
}
