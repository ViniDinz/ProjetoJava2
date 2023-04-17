package main.java.br.ProjetoPizzaria.negocio;

import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.beans.Cliente;

public class ControladorCliente {
private IRepositorioGenerico<Cliente> repositorioClientes;
	
private static ControladorCliente instance;


	public ControladorCliente()
	{
		this.repositorioClientes = new RepositorioGenerico<>("Clientes.dat");
	}

	public static ControladorCliente getInstance() {
	    if (instance == null) {
	        instance = new ControladorCliente();
	    }
	    return instance;
	}
	
	public void inserir(Cliente obj) throws ElementoJaExisteException, ObjetoVazioException
	{
		if(obj != null && obj.getCPF() != null && obj.getNome() != null && obj.getEndereco() != null)
		repositorioClientes.inserir(obj);
		else
		{
			throw new ObjetoVazioException(obj);
		}
	}
	
	public List<Cliente> listar() throws ElementoJaExisteException
	{
	 return repositorioClientes.listar();
	}
	
	public void remover(Cliente obj) throws ElementoNaoExisteException
	{
		repositorioClientes.remover(obj);
	}
	
	public void atualizar(Cliente obj, String nome, String CPF, String endereco) throws ElementoNaoExisteException
	{
		if(repositorioClientes.listar().contains(obj)) {
		obj.setNome(nome);
		obj.setCPF(CPF);
		obj.setEndereco(endereco);
		repositorioClientes.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}

}
