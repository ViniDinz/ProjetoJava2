package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;

public class Alimento implements Serializable{
	
	
    private String Nome;
	
	private double Preco;
	
	private TipoAlimento Tipo;
	
	public Alimento(String nome, double preco, TipoAlimento tipoalimento) {
		this.Nome =  nome;
		this.Preco = preco;
		this.Tipo = tipoalimento;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
        this.Nome = nome;
	}
	public double getPreco() {
		return Preco;
	}
	public void setPreco(double preco) {
		this.Preco = preco;
	}

	public TipoAlimento getTipo() {
		return Tipo;
	}
	public void setTipo(int valorEnum) {
		if(valorEnum == 0)
		{
			this.Tipo = TipoAlimento.COMIDA;
		}
		if(valorEnum == 1)
		{
			this.Tipo = TipoAlimento.BEBIDA;
		}
		if(valorEnum == 2)
		{
			this.Tipo = TipoAlimento.ACOMPANHAMENTOS;
		}
	}
	

}
