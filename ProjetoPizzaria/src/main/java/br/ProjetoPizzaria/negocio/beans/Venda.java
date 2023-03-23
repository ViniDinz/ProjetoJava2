package main.java.br.ProjetoPizzaria.negocio.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venda {

	private ArrayList<Alimentos> alimentos;
	
	private Funcionario funcionario;
	
	private LocalDateTime tempo_da_venda;

	public ArrayList<Alimentos> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(ArrayList<Alimentos> alimentos) {
		this.alimentos = alimentos;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDateTime getTempo_da_venda() {
		return tempo_da_venda;
	}

	public void setTempo_da_venda(LocalDateTime tempo_da_venda) {
		this.tempo_da_venda = tempo_da_venda;
	}
}
