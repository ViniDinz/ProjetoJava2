package main.java.br.ProjetoPizzaria.exception;

public class ObjetoVazioException extends Exception {
		
		private static final long serialVersionUID = -429602275837732324L;
		
		private Object elemento;
	    
	    public ObjetoVazioException (Object obj) {
	        super("Um objeto está vazio");
	        this.elemento = obj;        
	    }
	    
	    public Object getElemento() {
	        return elemento;
	    }

	    public void setElemento(Object elemento) {
	        this.elemento = elemento;
	    }

}
