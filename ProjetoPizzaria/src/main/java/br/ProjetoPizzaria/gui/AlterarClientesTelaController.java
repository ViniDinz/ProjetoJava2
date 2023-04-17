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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorCliente;
import main.java.br.ProjetoPizzaria.negocio.beans.Cliente;

public class AlterarClientesTelaController {

    @FXML
    private Button AlterarClienteButton;

    @FXML
    private ChoiceBox<Cliente> ClienteCB;

    @FXML
    private TextField ClienteCPFTextField;

    @FXML
    private TextField ClienteNomeTextField;
    
    @FXML
    private TextField ClienteEnderecoTextField;

    @FXML
    private Button VoltarButton;

    @FXML
    private Label label1;

    public void initialize() throws ElementoJaExisteException 
    {
    	ObservableList<Cliente> listaDeClientes = FXCollections.observableArrayList();
    	listaDeClientes.addAll(ControladorCliente.getInstance().listar());
    	
    	ClienteCB.setItems(listaDeClientes);
    }
    
    @FXML
    void AlterarClienteFunction(ActionEvent event) throws ElementoJaExisteException, ElementoNaoExisteException {
    	
    	if(!ClienteNomeTextField.getText().isBlank() && !ClienteCPFTextField.getText().isBlank() && !ClienteEnderecoTextField.getText().isBlank())
    	{
    		int test = 0;
    		String nome = ClienteNomeTextField.getText();
    		String cpf = ClienteCPFTextField.getText();
    		String endereco = ClienteEnderecoTextField.getText();
    		Cliente cliente = ClienteCB.getValue();
    		
    		for(int h = 0; h < ControladorCliente.getInstance().listar().size();h++)
    		{
    			if(ControladorCliente.getInstance().listar().get(h).getCPF().equals(cpf))
    			{
    				test = 1;
    			}
    		}
    		
    		if(test == 0)
    		{
    			ControladorCliente.getInstance().atualizar(cliente, nome, cpf,endereco);
    			label1.setText("Cliente alterado");
    			
    		}
    		else if(test == 1)
    		{
    			Alert alert = new Alert(AlertType.ERROR,"Já Existe um cliente com esse CPF");
       		    alert.show();
    		}
    	}
    }

    

    @FXML
    void VoltarFunction(ActionEvent event) {
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
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
 }
