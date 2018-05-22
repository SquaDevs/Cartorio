package br.com.cartorio.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.cartorio.entity.Senha;

public class SenhaDTO {

	private Senha senha;
	private Date previsaoIni;
	private Date previsaoFim;
	private String previsaoIniStr;
	private String previsaoFimStr;

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

	public String getPrevisaoIniStr() {
		return previsaoIniStr;
	}

	public void setPrevisaoIniStr(String previsaoIniStr) {
		this.previsaoIniStr = previsaoIniStr;
	}

	public String getPrevisaoFimStr() {
		return previsaoFimStr;
	}

	public void setPrevisaoFimStr(String previsaoFimStr) {
		this.previsaoFimStr = previsaoFimStr;
	}

	public static String calcularTempoMinutosStr(Date data, double minutosd) {
		int minutos = (int) minutosd;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MINUTE, minutos);
		Date novaData = calendar.getTime();
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dataFormat.format(novaData);
	}

	public static Date calcularTempoMinutos(Date data, double minutosd) {
		int minutos = (int) minutosd;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MINUTE, minutos);
		Date novaData = calendar.getTime();
		return novaData;
	}

}
