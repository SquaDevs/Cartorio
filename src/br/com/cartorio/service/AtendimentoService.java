package br.com.cartorio.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cartorio.dao.AtendimentoDAO;
import br.com.cartorio.entity.Atendimento;
import br.com.cartorio.entity.Senha;
import br.com.cartorio.entity.SubServico;

@Service
public class AtendimentoService {
	private AtendimentoDAO dao;
	
	@Autowired
	public AtendimentoService(AtendimentoDAO dao) {
		this.dao = dao;
	}
	
	public int inserirAtendimento(Atendimento atendimento)throws IOException{
		return dao.inserirAtendimento(atendimento);
	}
	
	public void fecharAtendimento(Atendimento atendimento) throws IOException {
		dao.fecharAtendimento(atendimento);
	}
	
	public void deletarAtendimento(Atendimento atendimento)throws IOException{
		dao.deletarAtendimento(atendimento);
	}
	
	public Atendimento listarAtendimento(int idAtendimento) throws IOException{
		return dao.listarAtendimento(idAtendimento);
	}
	
	public List<Atendimento> listarAtendimentos() throws IOException{
		return dao.listarAtendimentos();
	}
	
	public List<Atendimento> listarAtendimentosBySubServico(SubServico subServico) throws IOException{
		return dao.listarAtendimentosBySubServico(subServico);
	}
	
	public Atendimento listarAtendimentosBySubServicoAndSenha(SubServico subServico,Senha senha) throws IOException{
		return dao.listarAtendimentosBySubServicoAndSenha(subServico, senha);
	}

	public List<Atendimento> listarAtendimentosBySenha(Senha senha) throws IOException{
		return dao.listarAtendimentosBySenha(senha);
	}

}
