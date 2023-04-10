package main.java.br.ProjetoPizzaria.negocio;


import java.time.DayOfWeek;
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
				obj.getTempoDaVenda() != null && obj.getCliente() != null)
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
			if(repositorioVenda.listar().get(contadorVendaDiaria).getTempoDaVenda().getDayOfYear() == LocalDateTime.now().getDayOfYear() && 
					repositorioVenda.listar().get(contadorVendaDiaria).getTempoDaVenda().getYear() == LocalDateTime.now().getYear())
			{
				vendaDiarias.add(repositorioVenda.listar().get(contadorVendaDiaria));
			}
		}	
		return vendaDiarias;	
	}

	public List<Venda> listarVendasSemanais() throws ElementoJaExisteException
	{
		
		ArrayList<Venda> vendaSemanais = new ArrayList<Venda>();
		
		if(repositorioVenda.listar().get(repositorioVenda.listar().size() - 1).getTempoDaVenda().getDayOfWeek() != DayOfWeek.SUNDAY) {
		for(int contadorVendaSemanal =  repositorioVenda.listar().size() - 1; contadorVendaSemanal >= 0;contadorVendaSemanal--)
		{
		    if(repositorioVenda.listar().get(contadorVendaSemanal).getTempoDaVenda().getDayOfWeek() != DayOfWeek.SUNDAY)
		    {
		    	vendaSemanais.add(repositorioVenda.listar().get(contadorVendaSemanal));
		    }
		}
		}
		else if(repositorioVenda.listar().get(repositorioVenda.listar().size() -1).getTempoDaVenda().getDayOfWeek() == DayOfWeek.SUNDAY)
		{
			int contador = 1;
			while(repositorioVenda.listar().get(repositorioVenda.listar().size() - contador).getTempoDaVenda().getDayOfWeek() == DayOfWeek.SUNDAY)
			{
				vendaSemanais.add(repositorioVenda.listar().get(repositorioVenda.listar().size() - contador));
				contador++;
			}
			for(int contadorVendaSemanal2 =  repositorioVenda.listar().size() - contador; contadorVendaSemanal2 >= 0;contadorVendaSemanal2--)
			{
			    if(repositorioVenda.listar().get(contadorVendaSemanal2).getTempoDaVenda().getDayOfWeek() != DayOfWeek.SUNDAY)
			    {
			    	vendaSemanais.add(repositorioVenda.listar().get(contadorVendaSemanal2));
			    }
			}
		}
		return vendaSemanais;	
		}

	
	public List<Venda> listarVendasMensais() throws ElementoJaExisteException
	{
          ArrayList<Venda> vendaMensais = new ArrayList<Venda>();
		
		for(int contadorVendaMensais = 0; contadorVendaMensais < repositorioVenda.listar().size();contadorVendaMensais++)
		{
			if(repositorioVenda.listar().get(contadorVendaMensais).getTempoDaVenda().getMonth() == LocalDateTime.now().getMonth() && 
					repositorioVenda.listar().get(contadorVendaMensais).getTempoDaVenda().getYear() == LocalDateTime.now().getYear())
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
	
	public void atualizar(Venda obj, ArrayList<ItemVenda> itemVenda, Funcionario funcionario, LocalDateTime tempo, Cliente cliente ) throws ElementoNaoExisteException
	{
		if(repositorioVenda.listar().contains(obj)) {
		obj.setAlimentos(itemVenda);
		obj.setFuncionario(funcionario);
		obj.setTempoDaVenda(tempo);
		obj.setCliente(cliente);
		repositorioVenda.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}	
}
