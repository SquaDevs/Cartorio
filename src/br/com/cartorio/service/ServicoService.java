package br.com.cartorio.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cartorio.dao.ServicoDAO;
import br.com.cartorio.entity.Servico;

@Service
public class ServicoService {
	ServicoDAO dao;
	
	@Autowired
	public ServicoService(ServicoDAO dao) {
		this.dao = dao;
	}
	
	public int inserirServico(Servico servico) throws IOException {
		return dao.inserirServico(servico);
	}

	public void deletarServico(Servico servico) throws IOException {
		dao.deletarServico(servico);
	}

	public Servico listarServico(int idServico) throws IOException {
		return dao.listarServico(idServico);
	}

	public List<Servico> listarServicos() throws IOException {
		return dao.listarServicos();
	}

}
