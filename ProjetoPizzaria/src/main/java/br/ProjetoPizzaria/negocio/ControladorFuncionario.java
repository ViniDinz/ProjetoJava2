package main.java.br.ProjetoPizzaria.negocio;


import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;


public class ControladorFuncionario {

private IRepositorioGenerico<Funcionario> repositorioFuncionario;
	
private static ControladorFuncionario instance;


	private ControladorFuncionario()
	{
		this.repositorioFuncionario = new RepositorioGenerico<>("Funcionario.dat");
	}

	public static ControladorFuncionario getInstance() {
	    if (instance == null) {
	        instance = new ControladorFuncionario();
	    }
	    return instance;
	}
	
	public void inserir(Funcionario obj) throws ElementoJaExisteException, ObjetoVazioException
	{
		if(obj.getCPF().isEmpty() == false && obj.getEndereco().isEmpty() == false && obj.getNome().isEmpty() == false)
		repositorioFuncionario.inserir(obj);
		else
		{
          throw new ObjetoVazioException(obj);	
		}
	}
	
	public List<Funcionario> listar() throws ElementoJaExisteException
	{
	 return repositorioFuncionario.listar();
	}
	
	public void remover(Funcionario obj) throws ElementoNaoExisteException
	{
		repositorioFuncionario.remover(obj);
	}
	
	public void atualizar(Funcionario obj, String nome, String CPF, String Endereco ) throws ElementoNaoExisteException
	{
		if(repositorioFuncionario.listar().contains(obj)) {
		obj.setCPF(CPF);
		obj.setNome(nome);
		obj.setEndereco(Endereco);
		repositorioFuncionario.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}
}
