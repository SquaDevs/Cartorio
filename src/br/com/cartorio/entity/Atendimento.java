package br.com.cartorio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="atendimento")
public class Atendimento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String statusAtendimentoAndamento = "Atendimento em Andamento";
	private static final String statusAtendimentoFinalizado = "Atendimento Finalizado";

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(max=45)
	@Column(name="status")
	private String status;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@NotNull
	@Column(name="data_inicio")
	private Date data_inicio;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="data_fim")
	private Date data_fim;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_subservico")
	private SubServico subServico;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_senha")
	private Senha senha;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public SubServico getSubServico() {
		return subServico;
	}

	public void setSubServico(SubServico subServico) {
		this.subServico = subServico;
	}

	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public static String getStatusatendimentoandamento() {
		return statusAtendimentoAndamento;
	}

	public static String getStatusatendimentofinalizado() {
		return statusAtendimentoFinalizado;
	}

	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", status=" + status + ", data_inicio=" + data_inicio + ", data_fim="
				+ data_fim + ", subServico=" + subServico + ", senha=" + senha + "]";
	}
	
}
