package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;

public class Usuario implements Serializable {
	
	private String login;
	
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString()
	{
		return login;
	}
}
