package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;

public class Funcionario implements Serializable  {
	
	private String nome;
	
	private String CPF;
	
	private String Endereco;

	public Funcionario(String nome, String cpf, String endereco) {
		this.nome = nome;
		this.CPF = cpf;
		this.Endereco = endereco;
	}

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

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

}
