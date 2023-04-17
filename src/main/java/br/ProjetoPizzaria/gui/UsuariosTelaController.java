package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorUsuario;
import main.java.br.ProjetoPizzaria.negocio.beans.Usuario;

public class UsuariosTelaController {

    @FXML
    private Button AdicionarUsuarioButton;

    @FXML
    private Button AlterarUsuarioButton;

    @FXML
    private Button ExcluirUsuarioButton;

    @FXML
    private TableColumn<Usuario, String> LoginColumn;

    @FXML
    private TableView<Usuario> UsuarioTableView;

    @FXML
    private Button VoltarButton;
    
    @FXML
    public void initialize() throws ElementoJaExisteException 
    {
    	LoginColumn.setCellValueFactory(new PropertyValueFactory<Usuario, String>("Login"));
    	UsuarioTableView.setEditable(true);
    	UsuarioTableView.setItems(listaDeUsuarios());
    }
    
    public ObservableList<Usuario> listaDeUsuarios() throws ElementoJaExisteException
    {
    	ObservableList<Usuario> listaDeUsuario = FXCollections.observableArrayList();
    	listaDeUsuario.addAll(ControladorUsuario.getInstance().listar());
		return listaDeUsuario;
    }
    
    @FXML
    void AdicionarUsuarioFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AdicionarUsuariosTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Adicionar Usuario");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void AlterarUsuarioFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AlterarUsuariosTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Alterar Usuario");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void ExcluirUsuarioFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("ExcluirUsuarioTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Excluir Usuario");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void VoltarFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
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

