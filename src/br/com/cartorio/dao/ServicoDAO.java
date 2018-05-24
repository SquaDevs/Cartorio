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

	/**
	 * Insere o Servico passado como parametro
	 * 
	 * @param servico Um objeto do tipo Servico indicando o servico a ser inserido
	 * @return  O id do servico que foi inserido
	 * @throws IOException
	 */
	public int inserirServico(Servico servico) throws IOException {
		manager.persist(servico);
		return servico.getId();
	}

	/**
	 * Deleta o servico passado como parametro
	 * 
	 * @param atendimento Um objeto do tipo Servico indicando o atendimento a ser inserido
	 * @return  O id do servico que foi inserido
	 * @throws IOException
	 */
	public void deletarServico(Servico servico) throws IOException {
		manager.remove(servico);
	}

	/**
	 * Lista ou seleciona o servico atraves do idServico passado como parametro
	 * 
	 * @param atendimento Um objeto do tipo Servico indicando o atendimento a ser inserido
	 * @return  O id do servico que foi inserido
	 * @throws IOException
	 */
	public Servico listarServico(int idServico) throws IOException {
		return manager.find(Servico.class,idServico);
	}

	/**
	 * Lista todos os servicos exitentes
	 * 
	 * @return  Um objeto do tipo List<Servico> com os servicos
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Servico> listarServicos() throws IOException {
		String jpql = "select s from Servico s";
		Query query = manager.createQuery(jpql);
		List<Servico> result = query.getResultList();
		return result;
	}

}
