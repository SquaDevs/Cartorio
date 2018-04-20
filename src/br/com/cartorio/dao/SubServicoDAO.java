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

	public int inserirSubServico(SubServico subServico, Servico servico) throws IOException {
		subServico.setServico(servico);
		manager.persist(subServico);
		return subServico.getId();
	}

	public void deletarSubServico(SubServico subServico) throws IOException {
		manager.remove(subServico);
	}

	public SubServico listarSubServico(int idSubServico) throws IOException {
		String jpql = "select s from SubServico s where s.id = :id";
		Query query = manager.createQuery(jpql);
		query.setParameter("id", idSubServico);
		SubServico result = (SubServico) query.getSingleResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<SubServico> listarSubServicos() throws IOException {
		String jpql = "select s from SubServico s ";
		Query query = manager.createQuery(jpql);
		List<SubServico> result = query.getResultList();
		return result;

	}
	@SuppressWarnings("unchecked")
	public List<SubServico> listarSubServicosByServico(Servico servico) throws IOException {
		String jpql = "select s from SubServico s "
				+ "where s.servico =:pServico";
		Query query = manager.createQuery(jpql);
		query.setParameter("pServico",servico);
		List<SubServico> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public int maxOrdem(SubServico subServico) throws IOException{
		String jpql = "select s from SubServico s "
				+ "where s.servico = :pServico";
		Query query = manager.createQuery(jpql);
		query.setParameter("pServico", subServico.getServico());
		List<SubServico> subServicoRetornado = query.getResultList();
		int result = 0;
		for (SubServico subServicos : subServicoRetornado) {
			if (subServicos.getOrdem() > result) {
				result = subServicos.getOrdem();
			}
		}
		return result;
	}
}
