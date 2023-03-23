package main.java.br.ProjetoPizzaria.negocio.beans;

public class Alimentos{
	
    private String nome;
	
	private double preco;
	
	private double custo;
	
	private String tipo;
	
	private double lucro;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getLucro() {
		return lucro;
	}
	public void setLucro() {
		this.lucro = this.getPreco() - this.getCusto();
	}

}
