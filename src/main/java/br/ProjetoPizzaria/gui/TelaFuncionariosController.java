package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorFuncionario;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;

public class TelaFuncionariosController {

    @FXML
    private Button AdicionarFuncionarioButton;

    @FXML
    private Button AlterarFuncionarioButton;

    @FXML
    private Button ExcluirFuncionarioButton;
    
    @FXML
    private Button VoltarButton;
    
    @FXML
    private TableColumn<Funcionario, String> FuncionarioCPFColumn;

    @FXML
    private TableColumn<Funcionario, String> FuncionarioEnderecoColumn;

    @FXML
    private TableColumn<Funcionario, String> FuncionarioNomeColumn;

    @FXML
    private TableView<Funcionario> ListaFuncionarioTBV;
    
    public void initialize() throws ElementoJaExisteException 
    {
    	
    	
    	FuncionarioNomeColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Nome"));
    	FuncionarioCPFColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("CPF"));
        FuncionarioEnderecoColumn.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Endereco"));
        
        ListaFuncionarioTBV.setItems(listaDeFuncionarios());    
    }
    
    public ObservableList<Funcionario> listaDeFuncionarios() throws ElementoJaExisteException
    {
    	ObservableList<Funcionario> listaDeFuncionarios = FXCollections.observableArrayList();
    	listaDeFuncionarios.addAll(ControladorFuncionario.getInstance().listar());
    	return listaDeFuncionarios;
    }

    @FXML
    void AdicionarFuncionarioFunction(ActionEvent event) {
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) AdicionarFuncionarioButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AdicionarFuncionariosTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Adicionar Funcionarios");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void AlterarFuncionarioFunction(ActionEvent event) {
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) AdicionarFuncionarioButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AlterarFuncionariosTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Alterar Funcionarios");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}


    }

    @FXML
    void ExcluirFuncionarioFunction(ActionEvent event) throws ElementoNaoExisteException, ElementoJaExisteException {
    	
    	if(ListaFuncionarioTBV.getSelectionModel().getSelectedItem() != null) {
    		Alert confirm = new Alert(AlertType.CONFIRMATION,"Tem certeza que quer apagar esse objeto?");
    		Optional<ButtonType> opcao = confirm.showAndWait();
    		if(opcao.get() == null)
    		{
    			
    		}
    		else if(opcao.get() == ButtonType.OK)
    		{   
    			Funcionario funcionario = ListaFuncionarioTBV.getSelectionModel().getSelectedItem();

    	    	ControladorFuncionario.getInstance().remover(funcionario);
    	    	ListaFuncionarioTBV.setItems(listaDeFuncionarios()); 
        	}
    		else if(opcao.get() == ButtonType.CANCEL)
    		{
    			
    		}
    		else
    		{
    			
    		}
    		
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

