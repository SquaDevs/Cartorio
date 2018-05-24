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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "senha")
public class Senha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String statusSenhaCriada = "Senha Criada";
	private static final String statusSenhaAndamento = "Senha em Andamento";
	private static final String statusSenhaEncerrada = "Senha Encerrada";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull
	@Column(name = "numero")
	private int numero;

	@Column(name = "preferencial")
	private Boolean preferencial;

	@Column(name = "status")
	private String status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull
	@Column(name = "data_inicio")
	private Date data_inicio;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "data_fim")
	private Date data_fim;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private Servico servico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Boolean getPreferencial() {
		return preferencial;
	}

	public void setPreferencial(Boolean preferencial) {
		this.preferencial = preferencial;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	public static String getStatusSenhaCriada() {
		return statusSenhaCriada;
	}

	public static String getStatusSenhaAndamento() {
		return statusSenhaAndamento;
	}

	public static String getStatusSenhaEncerrada() {
		return statusSenhaEncerrada;
	}

	@Override
	public String toString() {
		return "Senha [id=" + id + ", numero=" + numero + ", preferencial=" + preferencial + ", status=" + status
				+ ", data_inicio=" + data_inicio + ", data_fim=" + data_fim + ", servico=" + servico + "]";
	}

}
