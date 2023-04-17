package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorUsuario;
import main.java.br.ProjetoPizzaria.negocio.beans.Usuario;

public class ExcluirUsuarioTelaController {

    @FXML
    private Button ExcluirUsuarioButton;

    @FXML
    private PasswordField SenhaPassField;

    @FXML
    private ChoiceBox<Usuario> UsuarioCB;

    @FXML
    private Button VoltarButton;

    @FXML
    private Label label1;
    
    @FXML
    public void initialize() 
    {
    	 ObservableList<Usuario> listaDeUsuarios = FXCollections.observableArrayList();
    	 listaDeUsuarios.addAll(ControladorUsuario.getInstance().listar());
         UsuarioCB.setItems(listaDeUsuarios);
    }

    @FXML
    void ExcluirUsuarioFunction(ActionEvent event) throws ElementoNaoExisteException {
    	
    	if(UsuarioCB.getValue() != null && !SenhaPassField.getText().isBlank())
    	{
    		if(SenhaPassField.getText().equals(UsuarioCB.getValue().getSenha()) && ControladorUsuario.getInstance().listar().size() > 1)
    		{
    			ControladorUsuario.getInstance().remover(UsuarioCB.getValue());
    			label1.setText("Usuario Removido");
    		}
    		else if(!SenhaPassField.getText().equals(UsuarioCB.getValue().getSenha()))
    		{
          		 Alert alert = new Alert(AlertType.ERROR,"A senha está errada");
           		 alert.show();
    		}
    		else if(ControladorUsuario.getInstance().listar().size() <= 1)
    		{
    			Alert alert = new Alert(AlertType.ERROR,"So existe 1 usuario");
          		alert.show();
    		}
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
