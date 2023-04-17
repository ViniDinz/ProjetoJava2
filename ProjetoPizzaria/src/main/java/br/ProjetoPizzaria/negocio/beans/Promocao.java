package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Promocao implements Serializable {
	
	private int valor;
	
	private LocalDate tempoDeInicio;
	
	private LocalDate tempoDeFinalizacao;
	
	private ArrayList<Alimento> produtosValidos;
	
	public Promocao(int valor, LocalDate tempoDeInicio, LocalDate tempoDeFinalizacao, ArrayList<Alimento> ProdutosValidos)
	{
		this.valor = valor;
		this.tempoDeInicio = tempoDeInicio;
		this.tempoDeFinalizacao = tempoDeFinalizacao;
		this.produtosValidos = ProdutosValidos;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public LocalDate getTempoDeInicio() {
		return tempoDeInicio;
	}

	public void setTempoDeInicio(LocalDate tempoDeInicio) {
		this.tempoDeInicio = tempoDeInicio;
	}

	public LocalDate getTempoDeFinalizacao() {
		return tempoDeFinalizacao;
	}

	public void setTempoDeFinalizacao(LocalDate tempoDeFinalizacao) {
		this.tempoDeFinalizacao = tempoDeFinalizacao;
	}

	public ArrayList<Alimento> getProdutosValidos() {
		return produtosValidos;
	}

	public void setProdutosValidos(ArrayList<Alimento> produtosValidos) {
		this.produtosValidos = produtosValidos;
	}
	
	@Override
	public String toString()
	{
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		return valor + "%, " + tempoDeInicio.format(formatador) + ", " + tempoDeFinalizacao.format(formatador);
	}
	

}
