package main.java.br.ProjetoPizzaria.dados;

import java.util.Comparator;
import java.util.List;

import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;


public interface IRepositorioGenerico<T> {
    
    void inserir(T obj) throws ElementoJaExisteException;
    
    List<T> listar();
    
    List<T> listarOrdenado(Comparator<T> comparador);
    
    void remover(T obj) throws ElementoNaoExisteException;

    void atualizar(T newObj) throws ElementoNaoExisteException;

}
