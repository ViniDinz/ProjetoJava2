package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.negocio.ControladorUsuario;

public class LoginController {

    @FXML
    private Button CadastroButton;

    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField SenhaTextField;

    @FXML
    private TextField UsuarioTextField;
    
    public void initialize()
    {
    	if(ControladorUsuario.getInstance().listar().size() == 0)
    	{
    		CadastroButton.setVisible(true);
    	}
    }

    @FXML
    void CadastroFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) LoginButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Cadastro");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void LoginFunction(ActionEvent event) {
    	
    	int test = 0;
    	for(int a = 0; a < ControladorUsuario.getInstance().listar().size();a++)
    	{
    		if(ControladorUsuario.getInstance().listar().get(a).getLogin().equals(UsuarioTextField.getText()) 
    			&& ControladorUsuario.getInstance().listar().get(a).getSenha().equals(SenhaTextField.getText()))
    		{
    			test = 1;
    	    	Stage stage = new Stage();
    			try {
    				Stage closeScreen = (Stage) LoginButton.getScene().getWindow();
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
    			
    	}
    	
    	if(test ==0)
    	{
    		Alert alert = new Alert(AlertType.INFORMATION,"Login ou senha estão errados");
    		alert.show();
    	}
    	


    }

}


