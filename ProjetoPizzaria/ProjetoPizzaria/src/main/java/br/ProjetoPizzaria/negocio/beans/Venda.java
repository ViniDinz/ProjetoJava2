package main.java.br.ProjetoPizzaria.negocio.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venda implements Serializable {

	private ArrayList<ItemVenda> itemVenda;
	
	private Funcionario funcionario;
	
	private Cliente cliente;
	
	private LocalDateTime tempoDaVenda; //dataDeVenda//
	
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

	public LocalDateTime getTempoDaVenda() {
		return tempoDaVenda;
	}

	public void setTempoDaVenda(LocalDateTime tempoDaVendaObj) {
		this.tempoDaVenda = tempoDaVendaObj;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
