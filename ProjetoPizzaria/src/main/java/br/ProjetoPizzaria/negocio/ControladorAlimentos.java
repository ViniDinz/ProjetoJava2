package main.java.br.ProjetoPizzaria.negocio;


import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimentos;

public class ControladorAlimentos {

private IRepositorioGenerico<Alimentos> repositorioAlimentos;
	
	
	public ControladorAlimentos()
	{
		this.repositorioAlimentos = new RepositorioGenerico<>("Alimentos.dat");
	}

	
	public void inserir(Alimentos obj) throws ElementoJaExisteException
	{
		repositorioAlimentos.inserir(obj);
	}
	
	public List<Alimentos> listar() throws ElementoJaExisteException
	{
	 return repositorioAlimentos.listar();
	}
	
	public void remover(Alimentos obj) throws ElementoNaoExisteException
	{
		repositorioAlimentos.remover(obj);
	}
	
	public void atualizar(Alimentos obj, String nome, double preco, double custo, String tipo  ) throws ElementoNaoExisteException
	{
		if(repositorioAlimentos.listar().contains(obj)) {
		obj.setCusto(custo);
		obj.setPreco(preco);
		obj.setNome(nome);
		obj.setTipo(tipo);
		obj.setLucro();	
		repositorioAlimentos.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}
	
}
