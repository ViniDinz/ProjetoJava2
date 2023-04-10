package main.java.br.ProjetoPizzaria.negocio;

import java.time.LocalDateTime;
import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
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
		if(obj.getTempoDeFinalizacao() != LocalDateTime.now() && obj.getTempoDeFinalizacao() != null && obj.getTempoDeInicio() != null 
				&& obj.getValor() > 0 && obj.getValor() <= 100)
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
	
	public void atualizar(Promocao obj, LocalDateTime tempoDeInicio, LocalDateTime tempoDeFinalizacao, int valor  ) throws ElementoNaoExisteException
	{
		if(repositorioPromocao.listar().contains(obj)) {
        obj.setTempoDeFinalizacao(tempoDeFinalizacao);
        obj.setTempoDeInicio(tempoDeInicio);
        obj.setValor(valor);
		repositorioPromocao.atualizar(obj);
		}
		else
		{
			throw new ElementoNaoExisteException(obj);
		}		
	}
}
