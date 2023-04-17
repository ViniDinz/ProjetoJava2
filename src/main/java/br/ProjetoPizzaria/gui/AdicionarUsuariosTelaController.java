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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.ControladorUsuario;
import main.java.br.ProjetoPizzaria.negocio.beans.Usuario;

public class AdicionarUsuariosTelaController {

    @FXML
    private Button AdicionarUsuarioButton;

    @FXML
    private TextField LoginTextField;

    @FXML
    private PasswordField SenhaPassField;

    @FXML
    private Button VoltarButton;
    
    @FXML
    private Label label1;


    @FXML
    void AdicionarUsuarioFunction(ActionEvent event) throws ElementoJaExisteException, ObjetoVazioException {
    	
    	if(!LoginTextField.getText().isBlank() && !SenhaPassField.getText().isBlank())
    	{
    		Usuario usuario = new Usuario();
    		usuario.setLogin(LoginTextField.getText());
    		usuario.setSenha(SenhaPassField.getText());
    		ControladorUsuario.getInstance().inserir(usuario);
    		label1.setText("Usuario Cadastrado");
    	}
    	else
    	{
   		 Alert alert = new Alert(AlertType.ERROR,"Um elemento está vazio");
   		 alert.show();
    	}

    }

    @FXML
    void VoltarFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("UsuariosTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Usuarios");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
