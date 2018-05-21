package br.com.cartorio.dto;

import java.io.Serializable;

import br.com.cartorio.entity.Senha;
import br.com.cartorio.entity.SubServico;

public class AtendimentoDTO implements Serializable{

	private SubServico subServico;
	private Senha senha;

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

	@Override
	public String toString() {
		return "AtendimentoDTO [subServico=" + subServico + ", senha=" + senha + "]";
	}
	
	
}
