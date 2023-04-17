package main.java.br.ProjetoPizzaria.negocio;


import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;

public class ControladorAlimentos {

private IRepositorioGenerico<Alimento> repositorioAlimentos;
	
private static ControladorAlimentos instance;
	
	public ControladorAlimentos()
	{
		this.repositorioAlimentos = new RepositorioGenerico<>("Alimentos.dat");
	}
	
	public static ControladorAlimentos getInstance() {
	    if (instance == null) {
	        instance = new ControladorAlimentos();
	    }
	    return instance;
	}
	public void inserir(Alimento obj) throws ElementoJaExisteException, ObjetoVazioException
	{
		if(obj != null && obj.getNome() != null && obj.getPreco() != 0 && obj.getTipo() != null)
		repositorioAlimentos.inserir(obj);
		else
		{
			throw new ObjetoVazioException(obj);
		}
	}
	
	public List<Alimento> listar() 
	{
	 return repositorioAlimentos.listar();
	}
	
	public void remover(Alimento obj) throws ElementoNaoExisteException
	{
		repositorioAlimentos.remover(obj);
	}
	
	public void atualizar(Alimento obj, String nome, double preco,  int valorEnum  ) throws ElementoNaoExisteException
	{
		if(repositorioAlimentos.listar().contains(obj)) {
		obj.setPreco(preco);
		obj.setNome(nome);
		obj.setTipo(valorEnum);
		repositorioAlimentos.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}
	
}
