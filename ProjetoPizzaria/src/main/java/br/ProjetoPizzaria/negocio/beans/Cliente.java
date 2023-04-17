package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;

public class Cliente implements Serializable {
	
	private String nome;
	
	private String CPF;
	
	private String Endereco;
		
	public Cliente(String nome, String cpf, String endereco)
	{
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

	@Override
	public String toString()
	{
		return nome;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

}
