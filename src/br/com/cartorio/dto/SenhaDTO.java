package br.com.cartorio.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.cartorio.entity.Senha;

public class SenhaDTO {

	private Senha senha;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date previsaoIni;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date previsaoFim;


	public Senha getSenha() {
		return senha;
	}

	public void setSenha(Senha senha) {
		this.senha = senha;
	}

	public Date getPrevisaoIni() {
		return previsaoIni;
	}

	public void setPrevisaoIni(Date previsaoIni) {
		this.previsaoIni = previsaoIni;
	}

	public Date getPrevisaoFim() {
		return previsaoFim;
	}

	public void setPrevisaoFim(Date previsaoFim) {
		this.previsaoFim = previsaoFim;
	}

	public static Date calcularTempoMinutos(Date data, double minutosd) {
		int minutos = (int) minutosd;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MINUTE, minutos);
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date novaData = null;
		
		if(minutos > 0) {
			try {
				novaData = dataFormat.parse((dataFormat.format(calendar.getTime())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return novaData;
	}

}
