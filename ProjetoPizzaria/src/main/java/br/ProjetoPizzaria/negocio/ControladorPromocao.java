package main.java.br.ProjetoPizzaria.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;
import main.java.br.ProjetoPizzaria.negocio.beans.Promocao;

public class ControladorPromocao {

	private IRepositorioGenerico<Promocao> repositorioPromocao;
	
	private static ControladorPromocao instance;
	
	
	public ControladorPromocao()
	{
		this.repositorioPromocao = new RepositorioGenerico<>("Promocao.dat");
	}

	public static ControladorPromocao getInstance() {
	    if (instance == null) {
	        instance = new ControladorPromocao();
	    }
	    return instance;
	}
	
	
	public void inserir(Promocao obj) throws ElementoJaExisteException, ObjetoVazioException
	{
		if(obj.getTempoDeFinalizacao() != null && obj.getTempoDeInicio() != null
			&& obj.getValor() > 0 && obj.getValor() <= 100 && !obj.getProdutosValidos().isEmpty())
		repositorioPromocao.inserir(obj);
		else
		{
			throw new ObjetoVazioException(obj);
		}
	}
	
	public List<Promocao> listar() throws ElementoJaExisteException
	{
	 return repositorioPromocao.listar();
	}
	
	public void remover(Promocao obj) throws ElementoNaoExisteException
	{
		repositorioPromocao.remover(obj);
	}
	
	public void atualizar(Promocao obj, LocalDate tempoDeInicio, LocalDate tempoDeFinalizacao, int valor, ArrayList<Alimento> produtosValidos) throws ElementoNaoExisteException
	{
		if(repositorioPromocao.listar().contains(obj)) {
        obj.setTempoDeFinalizacao(tempoDeFinalizacao);
        obj.setTempoDeInicio(tempoDeInicio);
        obj.setValor(valor);
        obj.setProdutosValidos(produtosValidos);
		repositorioPromocao.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}
	
	public void  limparPromocoesExpiradas() throws ElementoNaoExisteException
	{
		for(int contador = 0; contador < repositorioPromocao.listar().size(); contador++)
		{
			if(repositorioPromocao.listar().get(contador).getTempoDeFinalizacao().isBefore(LocalDate.now()))
			{
				remover(repositorioPromocao.listar().get(contador));
				contador--;
			}
		}
	}
}
