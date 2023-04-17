package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.ControladorUsuario;
import main.java.br.ProjetoPizzaria.negocio.beans.Usuario;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastroController {

    @FXML
    private Button CadastrarButton;

    @FXML
    private TextField LoginTextField;

    @FXML
    private PasswordField SenhaPassField;

    @FXML
    private Label label1;
    
    @FXML
    void CadastrarFunction(ActionEvent event) throws ElementoJaExisteException, ObjetoVazioException {
    	
    	if(!LoginTextField.getText().isBlank() && !SenhaPassField.getText().isBlank())
    	{
    		int test = 0;
    		
    		for(int a = 0; a < ControladorUsuario.getInstance().listar().size();a++)
    		{
    			if(ControladorUsuario.getInstance().listar().get(a).getLogin().equals(LoginTextField.getText()))
    				test=1;
    		}
    		if(test ==0)
    		{
    		Usuario usuario = new Usuario();
    		usuario.setLogin(LoginTextField.getText());
    		usuario.setSenha(SenhaPassField.getText());
    		ControladorUsuario.getInstance().inserir(usuario);
    		
    		Stage stage = new Stage();
    		try {
    			Stage closeScreen = (Stage) CadastrarButton.getScene().getWindow();
    			closeScreen.close();
    			Parent parent = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
    			Scene scene = new Scene(parent);
    			
    			stage.setTitle("Menu Principal");
    	    	stage.setScene(scene);
    	    	stage.show();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		}
    		else if(test == 1)
    		{
        		Alert alert = new Alert(AlertType.ERROR,"O usuario ja existe");
        		alert.show();
    		}
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Campo vazio");
    		alert.show();

    	}

    }


}
