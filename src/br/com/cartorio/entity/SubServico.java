package br.com.cartorio.entity;

import java.io.Serializable;

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

@Entity
@Table(name="subservico")
public class SubServico implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull
	@Size(max=45)
	@Column(name="nome")
	private String nome;
	
	@NotNull
	@Size(max=45)
	@Column(name="sigla")
	private String sigla;
	
	@NotNull
	@Column(name="ordem")
	private int ordem;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_servico")
	private Servico servico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@Override
	public String toString() {
		return "SubServico [id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", ordem=" + ordem + ", servico="
				+ servico + "]";
	}

}
