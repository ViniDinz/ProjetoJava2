package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button CadastroButton;

    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField SenhaTextField;

    @FXML
    private TextField UsuarioTextField;

    @FXML
    void CadastroFunction(ActionEvent event) {

    }

    @FXML
    void LoginFunction(ActionEvent event) {
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) LoginButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
			Scene scene = new Scene(parent);
			
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}


