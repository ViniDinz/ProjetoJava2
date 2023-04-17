package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipalController {

    @FXML
    private Button ListaAlimentosButton;

    @FXML
    private Button ListaFuncionariosButton;

    @FXML
    private Button ListaVendasButton;
    
    @FXML
    private Button ClientesButton;
    
    @FXML
    private Button UsuariosButton;

    @FXML
    void ListaAlimentosButton(ActionEvent event) {
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) ListaAlimentosButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaAlimentos.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Alimentos");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	

    }

    @FXML
    void ListaFuncionariosFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) ListaAlimentosButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Funcionarios");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void ListaVendasFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) ListaAlimentosButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaVendas.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Vendas");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    @FXML
    void ClientesFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) ListaAlimentosButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaClientes.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Clientes");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void UsuariosFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) UsuariosButton.getScene().getWindow();
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