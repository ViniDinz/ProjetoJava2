package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Venda implements Serializable {

	private ArrayList<ItemVenda> itemVenda;
	
	private Funcionario funcionario;
	
	private Cliente cliente;
	
	private LocalDateTime DataDeVenda; 
	
	private double PrecoTotal;
	
	public ArrayList<ItemVenda> getAlimentos() {
		return itemVenda;
	}

	public void setAlimentos(ArrayList<ItemVenda> itemVendaObj) {
		this.itemVenda = itemVendaObj;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDateTime getDataDeVenda() {
		return DataDeVenda.truncatedTo(ChronoUnit.SECONDS);
	}

	public void setDataDeVenda(LocalDateTime tempoDaVendaObj) {
		this.DataDeVenda = tempoDaVendaObj;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getPrecoTotal() {
		return PrecoTotal;
	}

	public void setPrecoTotal(double precoTotal) {
		this.PrecoTotal = precoTotal;
	}
	
	@Override
	public String toString()
	{
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		return this.DataDeVenda.format(formatador) + " " + this.funcionario.getNome() + " " + this.cliente.getNome();
	}

}
