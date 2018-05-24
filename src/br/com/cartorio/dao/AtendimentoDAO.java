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

	/**
	 * Insere o atendimento passado como parametro
	 * 
	 * @param atendimento Um objeto do tipo Atendimento indicando o atendimento a ser inserido
	 * @return  O id do atendimento que foi inserido
	 * @throws IOException
	 */
	public int inserirAtendimento(Atendimento atendimento) throws IOException {
		atendimento.setStatus(Atendimento.getStatusatendimentoandamento());
		atendimento.setData_inicio(new Date());
		manager.persist(atendimento);
		return atendimento.getId();
	}
	
	/**
	 * Fecha ou finaliza o atendimento passado como parametro
	 * 
	 * @param atendimento Um objeto do tipo Atendimento indicando o atendimento a ser finalizado
	 * @throws IOException
	 */
	public void fecharAtendimento(Atendimento atendimento) throws IOException {
		Atendimento atendimentoAlterado = manager.find(Atendimento.class, atendimento.getId());
		atendimentoAlterado.setStatus(Atendimento.getStatusatendimentofinalizado());
		atendimentoAlterado.setData_fim(new Date());
		manager.merge(atendimentoAlterado);
	}

	/**
	 * Deleta o atendimento passado como parametro
	 * 
	 * @param atendimento Um objeto do tipo Atendimento indicando o atendimento a ser deletar
	 * @throws IOException
	 */
	public void deletarAtendimento(Atendimento atendimento) throws IOException {
		manager.remove(atendimento);
	}

	/**
	 * Lista ou seleciona um atendimento  atraves do id passado como parametro
	 * 
	 * @param atendimento Um int como id do atendimento a ser listado
	 * @return  Um objeto do tipo Atendimento
	 * @throws IOException
	 */
	public Atendimento listarAtendimento(int idAtendimento) throws IOException {
		return manager.find(Atendimento.class, idAtendimento);
	}

	/**
	 * Lista todos os atendimentos existentes
	 *
	 * @return  Um objeto do tipo List<Atendimento> com os atendimentos
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimentos() throws IOException {
		String jpql = "Select a from Atendimento a";
		return manager.createQuery(jpql).getResultList();
	}

	/**
	 * Lista todos os atendimentos existentes em um subServico
	 *
	 * @param subServico Um objeto do tipo SubServico indicando para qual subServico  sera gerada a lista
	 * @return  Um objeto do tipo List<Atendimento> com os atendimentos
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimentosBySubServico(SubServico subServico) throws IOException {
		String jpql = "Select a from Atendimento a" + "where a.subServico = :pSubServico";
		Query query = manager.createQuery(jpql);
		query.setParameter("pSubServico", subServico);

		return query.getResultList();
	}

	/**
	 * Lista ou seleciona um atendimento existente em um subServico e uma senha
	 *
	 * @param subServico Um objeto do tipo SubServico indicando para qual subServico sera gerada a lista
	 * @param senha Um objeto do tipo Senha indicando para qual senhas sera gerada a lista
	 * @return  Um objeto do tipo List<Atendimento> com os atendimentos
	 * @throws IOException
	 */
	public Atendimento listarAtendimentosBySubServicoAndSenha(SubServico subServico, Senha senha)
			throws IOException {
		String jpql = "Select a from Atendimento a " + "where a.subServico = :pSubServico and " + "a.senha = :pSenha";
		Query query = manager.createQuery(jpql);
		query.setParameter("pSubServico", subServico);
		query.setParameter("pSenha", senha);
		return (Atendimento) query.getSingleResult();

	}

	/**
	 * Lista ou seleciona um atendimento existente em uma senha
	 *
	 * @param senha Um objeto do tipo Senha indicando para qual senhas sera gerada a lista
	 * @return  Um objeto do tipo List<Atendimento> com os atendimentos
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public List<Atendimento> listarAtendimentosBySenha(Senha senha) throws IOException {
		String jpql = "Select a from Atendimento a" + "where a.subServico = :pSenha";
		Query query = manager.createQuery(jpql);
		query.setParameter("pSenha", senha);

		return query.getResultList();
	}

}
