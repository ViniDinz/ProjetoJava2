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

import main.java.br.ProjetoPizzaria.negocio.ControladorCliente;
import main.java.br.ProjetoPizzaria.negocio.ControladorVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Cliente;

public class TelaClientesController {

    @FXML
    private Button AdicionarClienteButton;

    @FXML
    private Button AtualizarClienteButton;

    @FXML
    private TableColumn<Cliente, String> ClienteCpfColumn;

    @FXML
    private TableColumn<Cliente, String> ClienteNomeColumn;
    
    @FXML
    private TableColumn<Cliente, String> ClienteEnderecoColumn;

    @FXML
    private TableView<Cliente> ClienteTableView;

    @FXML
    private Button ExcluirClienteButton;

    @FXML
    private Button VoltarButton;
  
    @FXML
    public void initialize() throws ElementoJaExisteException 
    {
    	
    	
    	ClienteNomeColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
    	ClienteCpfColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("CPF"));  
    	ClienteEnderecoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Endereco")); 
        ClienteTableView.setEditable(true);
        ClienteTableView.setItems(listaDeClientes());    
    }   
    
    public ObservableList<Cliente> listaDeClientes() throws ElementoJaExisteException {
		ObservableList<Cliente> listaDeClientes = FXCollections.observableArrayList();
		listaDeClientes.addAll(ControladorCliente.getInstance().listar());
		return listaDeClientes;
	}

    @FXML
    void AdicionarClienteFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) AdicionarClienteButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AdicionarClientesTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Adicionar Clientes");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void AtualizarClienteFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) AdicionarClienteButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AlterarClientesTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Alterar Clientes");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void ExcluirClienteFunction(ActionEvent event) throws ElementoNaoExisteException, ElementoJaExisteException {
    	
    	int test = 0;
    	for(int a = 0; a < ControladorVenda.getInstance().listar().size();a++)
    	{
    		if(ControladorVenda.getInstance().listar().get(a).getCliente().equals(ClienteTableView.getSelectionModel().getSelectedItem()))
    		{
    			test = 1;
    		}

    	}    		
    	if(test ==0) {
    		
    		if(ClienteTableView.getSelectionModel().getSelectedItem() != null) {
        		Alert confirm = new Alert(AlertType.CONFIRMATION,"Tem certeza que quer apagar esse objeto?");
        		Optional<ButtonType> opcao = confirm.showAndWait();
        		if(opcao.get() == null)
        		{
        			
        		}
        		else if(opcao.get() == ButtonType.OK)
        		{               		
        	        Cliente cliente = ClienteTableView.getSelectionModel().getSelectedItem();
        	    	ControladorCliente.getInstance().remover(cliente);
        	    	ClienteTableView.setItems(listaDeClientes()); 
            	}
        		else if(opcao.get() == ButtonType.CANCEL)
        		{
        			
        		}
        		else
        		{
        			
        		}
    		}
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR,"O cliente ainda faz parte de alguma venda");
    		alert.show();
    	}

    }
	@FXML
    void VoltarFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) AdicionarClienteButton.getScene().getWindow();
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

