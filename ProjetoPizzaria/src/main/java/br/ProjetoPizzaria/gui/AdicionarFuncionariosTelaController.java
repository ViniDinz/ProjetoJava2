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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.ControladorFuncionario;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;

public class AdicionarFuncionariosTelaController {

    @FXML
    private Button AdicionarFuncionarioButton;

    @FXML
    private TextField FuncionarioCPFTextField;

    @FXML
    private TextField FuncionarioEnderecoTextField;

    @FXML
    private TextField FuncionarioNomeTextField;
    
    @FXML
    private Label label1;

    @FXML
    private Button VoltarButton;

    @FXML
    void AdicionarFuncionarioFunction(ActionEvent event) throws ElementoJaExisteException, ObjetoVazioException {
    	
    	
    	
    	if(!FuncionarioNomeTextField.getText().isBlank() && !FuncionarioCPFTextField.getText().isBlank() && !FuncionarioEnderecoTextField.getText().isBlank()) {
    	int test = 0;
    	String nome = FuncionarioNomeTextField.getText();
    	String cpf = FuncionarioCPFTextField.getText();
    	String endereco = FuncionarioEnderecoTextField.getText();   	
    	Funcionario funcionario = new Funcionario(nome,cpf,endereco);
    	
    	for(int h = 0; h < ControladorFuncionario.getInstance().listar().size();h++)
    	{
    		if(ControladorFuncionario.getInstance().listar().get(h).getCPF().equals(cpf))
    		{
    			test = 1;
    		}
    	}
    	if(test == 0) {
    	ControladorFuncionario.getInstance().inserir(funcionario);
    	label1.setText("O Funcionario foi inserido");
    	}
    	else if(test == 1)
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Já existe um funcionario com esse CPF");
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
    			Parent parent = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
    			Scene scene = new Scene(parent);
    			
    			stage.setTitle("Funcionarios");
    	    	stage.setScene(scene);
    	    	stage.show();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}


    }

}
