package main.java.br.ProjetoPizzaria.negocio;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimentos;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;
import main.java.br.ProjetoPizzaria.negocio.beans.Venda;

public class ControladorVenda {
	
	private IRepositorioGenerico<Venda> repositorioVenda;
	
	
	private ControladorVenda()
	{
		this.repositorioVenda = new RepositorioGenerico<>("Venda.dat");
	}

	
	public void inserir(Venda obj) throws ElementoJaExisteException
	{
		repositorioVenda.inserir(obj);
	}
	
	public List<Venda> listar() throws ElementoJaExisteException
	{
	 return repositorioVenda.listar();
	}
	
	public void remover(Venda obj) throws ElementoNaoExisteException
	{
		repositorioVenda.remover(obj);
	}
	
	public void atualizar(Venda obj, ArrayList<Alimentos> alimentos, Funcionario funcionario, LocalDateTime tempo ) throws ElementoNaoExisteException
	{
		if(repositorioVenda.listar().contains(obj)) {
		obj.setAlimentos(alimentos);
		obj.setFuncionario(funcionario);
		obj.setTempo_da_venda(tempo);
		repositorioVenda.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}
	
}
