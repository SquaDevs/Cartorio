package br.com.cartorio.dao;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import br.com.cartorio.entity.Servico;

@Repository
public class ServicoDAO {

	@PersistenceContext
	EntityManager manager;

	public int inserirServico(Servico servico) throws IOException {
		manager.persist(servico);
		return servico.getId();
	}

	public void deletarServico(Servico servico) throws IOException {
		manager.remove(servico);
	}

	public Servico listarServico(int idServico) throws IOException {
		return manager.find(Servico.class,idServico);
	}

	@SuppressWarnings("unchecked")
	public List<Servico> listarServicos() throws IOException {
		String jpql = "select s from Servico s";
		Query query = manager.createQuery(jpql);
		List<Servico> result = query.getResultList();
		return result;
	}

}
