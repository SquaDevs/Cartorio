package br.com.cartorio.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.cartorio.entity.Senha;
import br.com.cartorio.entity.Servico;
import br.com.cartorio.entity.SubServico;

@Repository
public class SenhaDAO {

	@PersistenceContext
	EntityManager manager;

	public int inserirSenha(Senha senha) throws IOException {
		senha.setStatus(Senha.getStatusSenhaCriada());
		senha.setData_inicio(new Date());
		manager.persist(senha);
		return senha.getId();
	}
	
	public void atenderSenha(Senha senha) throws IOException {
		Senha senhaAlterada = manager.find(Senha.class, senha.getId());
		senhaAlterada.setStatus(Senha.getStatusSenhaAndamento());
		manager.merge(senhaAlterada);
	}
	
	public void finalizarSenha(Senha senha) throws IOException {
		Senha senhaAlterada = manager.find(Senha.class, senha.getId());
		senhaAlterada.setStatus(Senha.getStatusSenhaEncerrada());
		senhaAlterada.setData_fim(new Date());
		manager.merge(senhaAlterada);
	}

	public void deletarSenha(Senha senha) throws IOException {
		manager.remove(senha);
	}

	public Senha listarSenha(int idSenha) throws IOException {
		Senha senha = manager.find(Senha.class, idSenha);
		return senha;
	}

	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhas() throws IOException {
		String jpql = "select s from Senha s";
		Query query = manager.createQuery(jpql);
		List<Senha> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasPainel() throws IOException {
		String jpql = "select s from Senha s "
					+ "where s.status != 'finalizado'";
		Query query = manager.createQuery(jpql);
		query.setMaxResults(5);
		List<Senha> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasByPrimeiroSubServico(SubServico subServico) throws IOException {
		String jpql = "select s from Atendimento a "
					+ " right join a.senha s "
					+ "where a.id is null and s.servico.id =:pServico";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("pServico", subServico.getServico().getId());
		List<Senha> result = query.getResultList();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasBySubServicoParaAtender(SubServico subServico) throws IOException {
		
		List<Integer> senhas = listarSenhasProximoSubServico(subServico);
		
		String jpql = null;
		
		if (senhas.isEmpty()) {
			jpql = "select s from Atendimento a "
					+ " inner join a.senha s "
					+ "where a.subServico.ordem =:pOrdem "
					+ "	 and a.status = 'Fechado' "
					+ "	 and s.servico.id =:pServico";
			
			Query query = manager.createQuery(jpql);
			query.setParameter("pOrdem", subServico.getOrdem() - 1);
			query.setParameter("pServico", subServico.getServico().getId());
			List<Senha> result = query.getResultList();
			
			return result;
			
		} else {
			jpql = "select s from Atendimento a "
					+ " inner join a.senha s "
					+ "where a.subServico.ordem =:pOrdem "
					+ "	 and a.status = 'Fechado' "
					+ "	 and s.servico.id =:pServico"
					+ "	 and s.id not in (:pSenhas)";
			
			Query query = manager.createQuery(jpql);
			query.setParameter("pOrdem", subServico.getOrdem() - 1);
			query.setParameter("pServico", subServico.getServico().getId());
			query.setParameter("pSenhas", senhas);
			List<Senha> result = query.getResultList();
			
			return result;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private List<Integer> listarSenhasProximoSubServico(SubServico subServico) throws IOException {
		String jpql = "select s.id from Atendimento a "
					+ "inner join a.senha s "
					+ "where a.subServico.ordem =:pOrdem "
					+ "	 and s.servico.id =:pServico";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("pOrdem", subServico.getOrdem());
		query.setParameter("pServico", subServico.getServico().getId());
		List<Integer> result = query.getResultList();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasBySubServico(SubServico subServico) throws IOException {
		String jpql = "select s from Atendimento a "
					+ " inner join a.senha s "
					+ "where a.subServico.id =:pSubServico ";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("pSubServico", subServico.getId());
		List<Senha> result = query.getResultList();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasBySubServicoEmAtendimento(SubServico subServico) throws IOException {
		String jpql = "select s from Atendimento a "
					+ " inner join a.senha s "
					+ "where a.subServico.id =:pSubServico and a.status = 'Em Atendimento'";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("pSubServico", subServico.getId());
		List<Senha> result = query.getResultList();
		
		return result;
	}
	
	public Senha listarSenhasByNumero(int numero, Servico servico) throws IOException {
		String jpql = "select s from Senha s "
				    + "where s.numero =:pNumero and s.servico.id =:pServico";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("pNumero", numero);
		query.setParameter("pServico", servico);
		Senha result = (Senha) query.getSingleResult();
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public int ultimoNumeroByServico(Servico servico) throws IOException{

		String jpql="Select s  from Senha s where s.servico.id = :pServico";
		Query query = manager.createQuery(jpql);
		query.setParameter("pServico", servico.getId());
		List<Senha> senhas = query.getResultList();
		int maior = 0;
		for (Senha senha : senhas) {
			if (senha.getNumero() > maior) {
				maior = senha.getNumero();
			}
		}
		return maior ;
	} 
}
