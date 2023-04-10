package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Promocao implements Serializable {
	
	private int valor;
	
	private LocalDateTime tempoDeInicio;
	
	private LocalDateTime tempoDeFinalizacao;

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public LocalDateTime getTempoDeInicio() {
		return tempoDeInicio;
	}

	public void setTempoDeInicio(LocalDateTime tempoDeInicio) {
		this.tempoDeInicio = tempoDeInicio;
	}

	public LocalDateTime getTempoDeFinalizacao() {
		return tempoDeFinalizacao;
	}

	public void setTempoDeFinalizacao(LocalDateTime tempoDeFinalizacao) {
		this.tempoDeFinalizacao = tempoDeFinalizacao;
	}

}
