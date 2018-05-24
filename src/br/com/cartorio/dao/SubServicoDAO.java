package br.com.cartorio.dao;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import br.com.cartorio.entity.Servico;
import br.com.cartorio.entity.SubServico;

@Repository
public class SubServicoDAO {

	@PersistenceContext
	EntityManager manager;

	/**
	 * Insere o subServico passado como parametro
	 * 
	 * @param subServico Um objeto do tipo SubServico indicando o subServico a ser inserido
	 * @param servico Um objeto do tipo Servico indicando o servico em qual o subServico sera inserido
	 * @return  O id do subServico que foi inserido
	 * @throws IOException
	 */
	public int inserirSubServico(SubServico subServico, Servico servico) throws IOException {
		subServico.setServico(servico);
		manager.persist(subServico);
		return subServico.getId();
	}

	/**
	 * Deleta o subServico passado como parametro
	 * 
	 * @param subServico Um objeto do tipo SubServico indicando o subServico a ser deletado
	 * @throws IOException
	 */
	public void deletarSubServico(SubServico subServico) throws IOException {
		manager.remove(subServico);
	}

	/**
	 * Lista ou seleciona um subServico atraves do idSubServico passado como parametro
	 * 
	 * @param subServico Um int idSubServico como id do subServico a ser listado
	 * @return  Um objeto do tipo SubServico
	 * @throws IOException
	 */
	public SubServico listarSubServico(int idSubServico) throws IOException {
		String jpql = "select s from SubServico s where s.id = :id";
		Query query = manager.createQuery(jpql);
		query.setParameter("id", idSubServico);
		SubServico result = (SubServico) query.getSingleResult();
		return result;
	}

	/**
	 * Lista todo os subServicos existentes
	 * 
	 * @return  Um objeto do tipo List<SubServico> com os subServicos
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<SubServico> listarSubServicos() throws IOException {
		String jpql = "select s from SubServico s ";
		Query query = manager.createQuery(jpql);
		List<SubServico> result = query.getResultList();
		return result;

	}
	
	/**
	 * Lista todos os subsServicos existente em um servico passado como parametro
	 * 
	 * @param servico Um objeto do tipo Servico
	 * @return  Um objeto do tipo List<SubServico> com os subServicos
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<SubServico> listarSubServicosByServico(Servico servico) throws IOException {
		String jpql = "select s from SubServico s "
				+ "where s.servico =:pServico";
		Query query = manager.createQuery(jpql);
		query.setParameter("pServico",servico);
		List<SubServico> result = query.getResultList();
		return result;
	}
	
	/**
	 * Metodo para buscar qual a ultima ordem de subServico em um Servico
	 * 
	 * @param servico Um objeto do tipo Servico
	 * @return  Um int indicando o ultimo numero da ordem
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public int maxOrdem(Servico servico) throws IOException{
		String jpql = "select s from SubServico s "
					+ "where s.servico = :pServico";
	
		Query query = manager.createQuery(jpql);
		query.setParameter("pServico", servico);
		
		
		List<SubServico> subServicoRetornado = query.getResultList();
		Integer result = 0;
		
		for (SubServico subServico : subServicoRetornado) {
			if (subServico.getOrdem() > result) {
				result = subServico.getOrdem();
			}
		}
		return result;
	}
}
