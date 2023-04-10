package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private Button VoltarButton;

    @FXML
    void AdicionarFuncionarioFunction(ActionEvent event) throws ElementoJaExisteException, ObjetoVazioException {
    	
    	String nome = FuncionarioNomeTextField.getText();
    	String cpf = FuncionarioCPFTextField.getText();
    	String endereco = FuncionarioEnderecoTextField.getText();
    	
    	Funcionario funcionario = new Funcionario(nome,cpf,endereco);
    	
    	ControladorFuncionario.getInstance().inserir(funcionario);

    }

    @FXML
    void VoltarFunction(ActionEvent event) {
    	
     	Stage stage = new Stage();
    		try {
    			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
    			closeScreen.close();
    			Parent parent = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
    			Scene scene = new Scene(parent);
    			
    	    	stage.setScene(scene);
    	    	stage.show();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}


    }

}
