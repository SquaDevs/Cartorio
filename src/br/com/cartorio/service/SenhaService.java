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
	
	@Autowired
	public SenhaService(SenhaDAO dao) {
		this.dao = dao;
	}
	
	public int inserirSenha(Senha senha) throws IOException {
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
	
	public List<Senha> listarSenhasBySubServico(SubServico subServico) throws IOException {
		return dao.listarSenhasBySubServico(subServico);
	}
	
	public List<Senha> listarSenhasByPrimeiroSubServico(SubServico subServico) throws IOException {
		return dao.listarSenhasByPrimeiroSubServico(subServico);
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
	
}
