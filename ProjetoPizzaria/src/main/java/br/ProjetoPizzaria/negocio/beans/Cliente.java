package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;

public class Cliente implements Serializable {
	
	private String nome;
	
	private String CPF;
	
	private int NumeroDeCompras;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public int getNumeroDeCompras() {
		return NumeroDeCompras;
	}

	public void setNumeroDeCompras(int numeroDeCompras) {
		NumeroDeCompras = numeroDeCompras;
	}

}
