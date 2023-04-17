package main.java.br.ProjetoPizzaria.negocio;

import java.util.List;

import main.java.br.ProjetoPizzaria.dados.IRepositorioGenerico;
import main.java.br.ProjetoPizzaria.dados.RepositorioGenerico;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.beans.Usuario;

public class ControladorUsuario {
	
	private IRepositorioGenerico<Usuario> repositorioUsuario;
	
	private static ControladorUsuario instance;
		
		public ControladorUsuario()
		{
			this.repositorioUsuario = new RepositorioGenerico<>("Usuarios.dat");
		}
		
		public static ControladorUsuario getInstance() {
		    if (instance == null) {
		        instance = new ControladorUsuario();
		    }
		    return instance;
		}
		
		public void inserir(Usuario obj) throws ElementoJaExisteException, ObjetoVazioException
		{
			if(obj != null  && obj.getLogin() != null && obj.getSenha() != null)
			repositorioUsuario.inserir(obj);
			else
			{
				throw new ObjetoVazioException(obj);
			}
		}
		
		public List<Usuario> listar() 
		{
		 return repositorioUsuario.listar();
		}
		
		public void remover(Usuario obj) throws ElementoNaoExisteException
		{
			repositorioUsuario.remover(obj);
		}
		
		public void atualizar(Usuario obj, String login,  String senha) throws ElementoNaoExisteException
		{
			if(repositorioUsuario.listar().contains(obj)) {
			obj.setLogin(login);
			obj.setSenha(senha);
			repositorioUsuario.atualizar(obj);
			}
			else
			{
				throw new ElementoNaoExisteException(obj);
			}		
		}

}
