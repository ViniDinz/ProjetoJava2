package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;

public class ItemVenda implements Serializable {
	
	private Alimento alimento;
	
	private int quantidade;
		
	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreçoTotal() {
		return alimento.getPreco() * quantidade;
	}

	@Override
	public String toString()
	{
		return this.alimento + ", " + this.quantidade;
	}

}
