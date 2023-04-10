package main.java.br.ProjetoPizzaria.negocio.beans;

public class ItemVenda {
	
	private Alimento alimento;
	
	private double quantidade;
		
	public Alimento getAlimentos() {
		return alimento;
	}

	public void setAlimentos(Alimento alimento) {
		this.alimento = alimento;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreçoTotal() {
		return alimento.getPreco() * quantidade;
	}


}
