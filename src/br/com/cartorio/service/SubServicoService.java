package br.com.cartorio.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cartorio.dao.SubServicoDAO;
import br.com.cartorio.entity.Servico;
import br.com.cartorio.entity.SubServico;

@Service
public class SubServicoService {
	SubServicoDAO dao;	
	
	@Autowired
	public SubServicoService(SubServicoDAO dao) {
		this.dao = dao;
	}
	
	public int inserirSubServico(SubServico subServico, Servico servico) throws IOException {
		return dao.inserirSubServico(subServico, servico);
	}

	public void deletarSubServico(SubServico subServico) throws IOException {
		dao.deletarSubServico(subServico);
	}

	public SubServico listarSubServico(int idSubServico) throws IOException {
		return dao.listarSubServico(idSubServico);
	}

	public List<SubServico> listarSubServicos() throws IOException {
		return dao.listarSubServicos();
	}
	
	public List<SubServico> listarSubServicosByServico(Servico servico) throws IOException {
		return dao.listarSubServicosByServico(servico);
	}
	
	public int maxOrdem(Servico servico) throws IOException{
		return dao.maxOrdem(servico);
	}
}