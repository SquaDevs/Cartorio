package br.com.cartorio.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.cartorio.entity.Atendimento;
import br.com.cartorio.entity.Senha;
import br.com.cartorio.entity.SubServico;

@Repository
public class AtendimentoDAO {
	@PersistenceContext
	EntityManager manager;

	public int inserirAtendimento(Atendimento atendimento) throws IOException {
		atendimento.setStatus(Atendimento.getStatusatendimentoandamento());
		atendimento.setData_inicio(new Date());
		manager.persist(atendimento);
		return atendimento.getId();
	}
	
	public void fecharAtendimento(Atendimento atendimento) throws IOException {
		Atendimento atendimentoAlterado = manager.find(Atendimento.class, atendimento.getId());
		atendimentoAlterado.setStatus(Atendimento.getStatusatendimentofinalizado());
		atendimentoAlterado.setData_fim(new Date());
		manager.merge(atendimentoAlterado);
	}

	public void deletarAtendimento(Atendimento atendimento) throws IOException {
		manager.remove(atendimento);
	}

	public Atendimento listarAtendimento(int idAtendimento) throws IOException {
		return manager.find(Atendimento.class, idAtendimento);
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimentos() throws IOException {
		String jpql = "Select a from Atendimento a";
		return manager.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimentosBySubServico(SubServico subServico) throws IOException {
		String jpql = "Select a from Atendimento a" + "where a.subServico = :pSubServico";
		Query query = manager.createQuery(jpql);
		query.setParameter("pSubServico", subServico);

		return query.getResultList();
	}

	public Atendimento listarAtendimentosBySubServicoAndSenha(SubServico subServico, Senha senha)
			throws IOException {
		String jpql = "Select a from Atendimento a " + "where a.subServico = :pSubServico and " + "a.senha = :pSenha";
		Query query = manager.createQuery(jpql);
		query.setParameter("pSubServico", subServico);
		query.setParameter("pSenha", senha);
		return (Atendimento) query.getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimentosBySenha(Senha senha) throws IOException {
		String jpql = "Select a from Atendimento a" + "where a.subServico = :pSenha";
		Query query = manager.createQuery(jpql);
		query.setParameter("pSenha", senha);

		return query.getResultList();
	}
	
	public Atendimento listarAtendimentosAnterior(Senha senha, SubServico subServico) throws IOException {
		String jpql = "Select a from Atendimento a " + "where a.senha.id = :pSenha and a.subServico.ordem = :pOrdem";
		Query query = manager.createQuery(jpql);
		query.setParameter("pSenha", senha.getId());
		query.setParameter("pOrdem", subServico.getOrdem() - 1);
		return (Atendimento) query.getSingleResult();
	}

}
