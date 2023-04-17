package main.java.br.ProjetoPizzaria.negocio;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.beans.Cliente;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;
import main.java.br.ProjetoPizzaria.negocio.beans.ItemVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Venda;

public class ControladorVenda {
	
	private IRepositorioGenerico<Venda> repositorioVenda;
	
	private static ControladorVenda instance;

	private ControladorVenda()
	{
		this.repositorioVenda = new RepositorioGenerico<>("Venda.dat");
	}

	public static ControladorVenda getInstance() {
	    if (instance == null) {
	        instance = new ControladorVenda();
	    }
	    return instance;
	}
	
	
	//Insere uma venda no repositorio se todos os seus atributos estiverem preenchidos//
	public void inserir(Venda obj) throws ElementoJaExisteException, ObjetoVazioException
	{
		if(obj != null && obj.getAlimentos() != null && !obj.getAlimentos().isEmpty() && obj.getFuncionario() != null && 
				obj.getDataDeVenda() != null)
		repositorioVenda.inserir(obj);
		else
		{
			throw new ObjetoVazioException(obj);
		}
	}
	
	public List<Venda> listar() throws ElementoJaExisteException
	{
	 return repositorioVenda.listar();
	}
	
	//Retorna uma lista que contem todas as vendas do dia de hoje//
	public List<Venda> listarVendasDiarias() throws ElementoJaExisteException
	{
		ArrayList<Venda> vendaDiarias = new ArrayList<Venda>();
		
		for(int contadorVendaDiaria = 0; contadorVendaDiaria < repositorioVenda.listar().size();contadorVendaDiaria++)
		{
			if(repositorioVenda.listar().get(contadorVendaDiaria).getDataDeVenda().getDayOfYear() == LocalDateTime.now().getDayOfYear() && 
					repositorioVenda.listar().get(contadorVendaDiaria).getDataDeVenda().getYear() == LocalDateTime.now().getYear())
			{
				vendaDiarias.add(repositorioVenda.listar().get(contadorVendaDiaria));
			}
		}	
		return vendaDiarias;	
	}

	public List<Venda> listarVendasAnuais() throws ElementoJaExisteException
	{
        ArrayList<Venda> vendaAnuais = new ArrayList<Venda>();
		
		for(int contadorVendaAnual = 0; contadorVendaAnual < repositorioVenda.listar().size();contadorVendaAnual++)
		{
			if(repositorioVenda.listar().get(contadorVendaAnual).getDataDeVenda().getYear() == LocalDateTime.now().getYear())
			{
				vendaAnuais.add(repositorioVenda.listar().get(contadorVendaAnual));
			}
		}	
		
		
		return vendaAnuais;	
		}

	
	public List<Venda> listarVendasMensais() throws ElementoJaExisteException
	{
          ArrayList<Venda> vendaMensais = new ArrayList<Venda>();
		
		for(int contadorVendaMensais = 0; contadorVendaMensais < repositorioVenda.listar().size();contadorVendaMensais++)
		{
			if(repositorioVenda.listar().get(contadorVendaMensais).getDataDeVenda().getMonth() == LocalDateTime.now().getMonth() && 
					repositorioVenda.listar().get(contadorVendaMensais).getDataDeVenda().getYear() == LocalDateTime.now().getYear())
			{
				vendaMensais.add(repositorioVenda.listar().get(contadorVendaMensais));
			}
		}		
		return vendaMensais;	
	}
	
	public void remover(Venda obj) throws ElementoNaoExisteException
	{
		repositorioVenda.remover(obj);
	}
	
	public void atualizar(Venda obj, ArrayList<ItemVenda> itemVenda, Funcionario funcionario, LocalDateTime tempo, Cliente cliente, double precoTotal ) throws ElementoNaoExisteException
	{
		if(repositorioVenda.listar().contains(obj)) {
		obj.setAlimentos(itemVenda);
		obj.setFuncionario(funcionario);
		obj.setDataDeVenda(tempo);
		obj.setCliente(cliente);
		obj.setPrecoTotal(precoTotal);
		repositorioVenda.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}	
}
