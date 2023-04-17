package main.java.br.ProjetoPizzaria.negocio.beans;

public enum TipoAlimento {
	
	COMIDA(0),BEBIDA(1),ACOMPANHAMENTOS(2);

	private final int valor;
	
	TipoAlimento(int valorOpcao)
	{
		valor = valorOpcao;
	}
	
	public int getValor()
	{
		return valor;
	}

}
