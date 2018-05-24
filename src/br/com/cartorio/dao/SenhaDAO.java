package br.com.cartorio.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cartorio.entity.Atendimento;
import br.com.cartorio.entity.Senha;
import br.com.cartorio.entity.Servico;
import br.com.cartorio.entity.SubServico;
import br.com.cartorio.service.SubServicoService;

@Repository
public class SenhaDAO {
	
	SubServicoService subServicoService;
	
	@Autowired
	public SenhaDAO(SubServicoService subServicoService) {
		this.subServicoService = subServicoService;
	}

	@PersistenceContext
	EntityManager manager;

	/**
	 * Insere a senha recebida como parametro, deixando seu status como 'Senha Criada' 
	 * e gerando a data no momento em que o metodo é criado
	 * 
	 * @param senha Um objeto do tipo Senha a ser inserido 
	 * @return  O id da senha que foi inserida, do tipo inteiro
	 * @throws IOException
	 * 
	 */
	public int inserirSenha(Senha senha) throws IOException {
		senha.setStatus(Senha.getStatusSenhaCriada());
		senha.setData_inicio(new Date());
		manager.persist(senha);
		return senha.getId();
	}

	/**
	 * Altera o status da senha receida como parametro para 'Senha em Andamento'
	 * 
	 * @param senha Um objeto do tipo Senha a ser atendida 
	 * @throws IOException
	 * 
	 */
	public void atenderSenha(Senha senha) throws IOException {
		Senha senhaAlterada = manager.find(Senha.class, senha.getId());
		senhaAlterada.setStatus(Senha.getStatusSenhaAndamento());
		manager.merge(senhaAlterada);
	}
	
	/**
	 * Altera o status da senha receida como parametro para 'Senha Encerrada'
	 * e gera data de finalização da senha
	 * 
	 * @param senha Um objeto do tipo Senha a ser finalizada 
	 * @throws IOException
	 * 
	 */
	public void finalizarSenha(Senha senha) throws IOException {
		Senha senhaAlterada = manager.find(Senha.class, senha.getId());
		senhaAlterada.setStatus(Senha.getStatusSenhaEncerrada());
		senhaAlterada.setData_fim(new Date());
		manager.merge(senhaAlterada);
	}

	/**
	 * Deleta a senha passada como parametro
	 * 
	 * @param senha Um objeto do tipo Senha a ser deletada 
	 * @throws IOException
	 * 
	 */
	public void deletarSenha(Senha senha) throws IOException {
		manager.remove(senha);
	}

	/**
	 * Lista ou Seleciona uma senha atraves do parametro idSenha
	 * 
	 * @param idSenha Uma variavel do tipo int, como id da senha
	 * @return  Um objeto do tipo Senha, correspondente ao id
	 * @throws IOException
	 * 
	 */
	public Senha listarSenha(int idSenha) throws IOException {
		Senha senha = manager.find(Senha.class, idSenha);
		return senha;
	}

	/**
	 * Lista todas as senha existentes
	 * 
	 * @return  Um objeto do tipo List<Senha>, com todas as senhas
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhas() throws IOException {
		String jpql = "select s from Senha s";
		Query query = manager.createQuery(jpql);
		List<Senha> result = query.getResultList();
		return result;
	}

	/**
	 * Lista ou Seleciona uma senha atraves do parametro id
	 * 
	 * @param id Uma variavel do tipo int, como id da senha
	 * @return  Um objeto do tipo Senha, correspondente ao id
	 * @throws IOException
	 * 
	 */
	public Senha listarSenhaPainel(int id) throws IOException {
		String jpql = "select s from Senha s " + "where s.status != '"+Senha.getStatusSenhaEncerrada()+"' "
				    + "and s.id = "+ id;
		Query query = manager.createQuery(jpql);
		query.setMaxResults(5);
		Senha senha = (Senha) query.getSingleResult();
		return senha;
	}
	
	/**
	 * Lista todas as senha existentes que não foram finalizadas
	 * 
	 * @return  Um objeto do tipo List<Senha>, com as senhas
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasPainel() throws IOException {
		String jpql = "select s from Senha s " + "where s.status != '"+Senha.getStatusSenhaEncerrada()+"'";
		Query query = manager.createQuery(jpql);
		query.setMaxResults(5);
		List<Senha> result = query.getResultList();
		return result;
	}
	
	/**
	 * Lista todas as senha existentes que não foram finalizadas e não estão em atendimento
	 * 
	 * @return  Um objeto do tipo List<Senha>, com as senhas
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Senha> chamarSenhasPainel() throws IOException {
		String jpql = "select s from Senha s " + "where s.status != '"+Senha.getStatusSenhaEncerrada()+"'"
				+ " and s.status != '"+Senha.getStatusSenhaAndamento()+"'";
		Query query = manager.createQuery(jpql);
		query.setMaxResults(5);
		List<Senha> result = query.getResultList();
		return result;
	}
	
	/**
	 * Lista as senhas que ainda não foram atendidas para o PRIMEIRO SUBSERVICO
	 * 
	 * @param subServico Uma variavel do tipo SubServico, indicando para qual SubServico 
	 * deseja utilizar o metodo
	 * @return  Um objeto do tipo List<Senha>, com as senhas
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasBySubServicoParaAtenderInicio(SubServico subServico) throws IOException {
		String jpql = "select s from Atendimento a " + " right join a.senha s "
				+ "where a.id is null and s.servico.id =:pServico";

		Query query = manager.createQuery(jpql);
		query.setParameter("pServico", subServico.getServico().getId());
		List<Senha> result = query.getResultList();

		return result;
	}
	
	
	/**
	 * Lista as senhas que ainda não foram atendidas para os SUBSERVICOS DE ORDEM 2 A 'N'
	 * 
	 * @param subServico Uma variavel do tipo SubServico, indicando para qual SubServico 
	 * deseja utilizar o metodo
	 * @return  Um objeto do tipo List<Senha>, com as senhas
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasBySubServicoParaAtender(SubServico subServico) throws IOException {

		List<Integer> senhas = listarSenhasProximoSubServico(subServico);

		String jpql = null;

		if (senhas.isEmpty()) {
			jpql = "select s from Atendimento a " + " inner join a.senha s " + "where a.subServico.ordem =:pOrdem "
					+ "	 and a.status = '"+Atendimento.getStatusatendimentofinalizado()+"' " + "	 and s.servico.id =:pServico";

			Query query = manager.createQuery(jpql);
			query.setParameter("pOrdem", subServico.getOrdem() - 1);
			query.setParameter("pServico", subServico.getServico().getId());
			List<Senha> result = query.getResultList();

			return result;

		} else {
			jpql = "select s from Atendimento a " + " inner join a.senha s " + "where a.subServico.ordem =:pOrdem "
					+ "	 and a.status = '"+Atendimento.getStatusatendimentofinalizado()+"' " + "	 and s.servico.id =:pServico"
					+ "	 and s.id not in (:pSenhas)";

			Query query = manager.createQuery(jpql);
			query.setParameter("pOrdem", subServico.getOrdem() - 1);
			query.setParameter("pServico", subServico.getServico().getId());
			query.setParameter("pSenhas", senhas);
			List<Senha> result = query.getResultList();

			return result;
		}

	}
	
	/**
	 * Metodo privado usado no proprio DAO para garantir que um atendimento finalizado não
	 * apareça novamente 
	 * 
	 * @param subServico Uma variavel do tipo SubServico, indicando para qual SubServico 
	 * deseja utilizar o metodo
	 * @return  Um objeto do tipo List<Integer>, com as senhas
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	private List<Integer> listarSenhasProximoSubServico(SubServico subServico) throws IOException {
		String jpql = "select s.id from Atendimento a " + "inner join a.senha s " + "where a.subServico.ordem =:pOrdem "
				+ "	 and s.servico.id =:pServico";

		Query query = manager.createQuery(jpql);
		query.setParameter("pOrdem", subServico.getOrdem());
		query.setParameter("pServico", subServico.getServico().getId());
		List<Integer> result = query.getResultList();

		return result;
	}

	/**
	 * Metodo para mostrar as senhas que ja tem atendimento com base em um 
	 * SubServico
	 * 
	 * @param subServico Uma variavel do tipo SubServico, indicando para qual SubServico 
	 * deseja utilizar o metodo
	 * @return  Um objeto do tipo List<Senha>, com as senhas
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasBySubServico(SubServico subServico) throws IOException {
		String jpql = "select s from Atendimento a " + " inner join a.senha s "
				+ "where a.subServico.id =:pSubServico ";

		Query query = manager.createQuery(jpql);
		query.setParameter("pSubServico", subServico.getId());
		List<Senha> result = query.getResultList();

		return result;
	}
	
	/**
	 * Metodo para mostrar as senhas que ja tem atendimento com base em um 
	 * SubServico e que estão em andamento
	 * 
	 * @param subServico Uma variavel do tipo SubServico, indicando para qual SubServico 
	 * deseja utilizar o metodo
	 * @return  Um objeto do tipo List<Senha>, com as senhas
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Senha> listarSenhasBySubServicoEmAtendimento(SubServico subServico) throws IOException {
		String jpql = "select s from Atendimento a " + " inner join a.senha s "
				+ "where a.subServico.id =:pSubServico and a.status = '"+Atendimento.getStatusatendimentoandamento()+"'";

		Query query = manager.createQuery(jpql);
		query.setParameter("pSubServico", subServico.getId());
		List<Senha> result = query.getResultList();

		return result;
	}

	
	/**
	 * Metodo para saber qual o numero da ultima senha
	 * 
	 * @param subServico Uma variavel do tipo SubServico, indicando para qual SubServico 
	 * deseja utilizar o metodo
	 * @return  Um int como numero da ultima senha
	 * @throws IOException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public int ultimoNumeroByServico(Servico servico) throws IOException {

		String jpql = "Select s  from Senha s where s.servico.id = :pServico";
		Query query = manager.createQuery(jpql);
		query.setParameter("pServico", servico.getId());
		List<Senha> senhas = query.getResultList();
		int maior = 0;
		for (Senha senha : senhas) {
			if (senha.getNumero() > maior) {
				maior = senha.getNumero();
			}
		}
		return maior;
	}
	
	/**
	 * Metodo para saber qual a media de previsão de inicio de atendimento
	 * 
	 * @param subServico Uma variavel do tipo Servico, indicando para qual Servico 
	 * deseja utilizar o metodo
	 * @return  Um objeto Double como a previsão em minutos
	 * @throws IOException
	 * 
	 */
	public Double previsaoInicio(Servico servico) throws IOException{
		
		String jpql = "Select avg(TIMESTAMPDIFF(minute, s.data_inicio, a.data_inicio)) "
					+ "from senha s "
					+ "inner join atendimento a "
					+ "	 on a.id_senha = s.id "
					+ "inner join subservico sub "
					+ "	 on sub.id = a.id_subservico "
					+ "where sub.ordem = 1 and sub.id_servico = :pServico";
		
		Query query = manager.createNativeQuery(jpql);
		query.setParameter("pServico", servico.getId());
		BigDecimal mediaBigDecimal = (BigDecimal) query.getSingleResult();
		Double media;
		
		if(mediaBigDecimal == null ) {
			media = 0.0;
		}else {
			media = mediaBigDecimal.doubleValue();
		}
		
		return media;
	}
	
	/**
	 * Metodo para saber qual a media de previsão de termino de atendimento
	 * 
	 * @param subServico Uma variavel do tipo Servico, indicando para qual Servico 
	 * deseja utilizar o metodo
	 * @return  Um objeto Double como a previsão em minutos
	 * @throws IOException
	 * 
	 */
	public Double previsaoTermino(Servico servico) throws IOException{
		
		Integer maxOrdem = subServicoService.maxOrdem(servico);

		String jpql = "Select avg(TIMESTAMPDIFF(minute, s.data_inicio, a.data_fim)) "
				+ "from senha s "
				+ "inner join atendimento a "
				+ "	 on a.id_senha = s.id "
				+ "inner join subservico sub "
				+ "	 on sub.id = a.id_subservico "
				+ "where sub.ordem = :pOrdem and sub.id_servico = :pServico";
	
		Query query = manager.createNativeQuery(jpql);
		query.setParameter("pServico", servico.getId());
		query.setParameter("pOrdem", maxOrdem);
		BigDecimal mediaBigDecimal = (BigDecimal) query.getSingleResult();
		Double media;
		
		if(mediaBigDecimal == null ) {
			media = 0.0;
		}else {
			media = mediaBigDecimal.doubleValue();
		}
		
		return media;
	}
}
