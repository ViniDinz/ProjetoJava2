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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorFuncionario;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;

public class AlterarFuncionariosTelaController {

    @FXML
    private Button AlterarFuncionarioButton;

    @FXML
    private TextField CPFFuncionarioTextField;

    @FXML
    private TextField EnderecoFuncionarioTextField;

    @FXML
    private ChoiceBox<Funcionario> FuncionarioCB;

    @FXML
    private TextField NomeFuncionarioTextField;

    @FXML
    private Button VoltarButton;
    
    public void initialize() throws ElementoJaExisteException 
    {
    	ObservableList<Funcionario> listaDeFuncionarios = FXCollections.observableArrayList();
    	listaDeFuncionarios.addAll(ControladorFuncionario.getInstance().listar());
    	
    	FuncionarioCB.setItems(listaDeFuncionarios);
    }

    @FXML
    void AlterarFuncionarioFunction(ActionEvent event) throws ElementoNaoExisteException, ElementoJaExisteException {
    	
    	
    	if(FuncionarioCB.getValue() != null && !NomeFuncionarioTextField.getText().isBlank() && !CPFFuncionarioTextField.getText().isBlank()
    			&& !EnderecoFuncionarioTextField.getText().isBlank()) {
    	int test = 0;
    	Funcionario funcionario =  FuncionarioCB.getValue();
    	String nome = NomeFuncionarioTextField.getText();
    	String cpf = CPFFuncionarioTextField.getText();
    	String endereco = EnderecoFuncionarioTextField.getText();
    	
    	for(int h = 0; h < ControladorFuncionario.getInstance().listar().size();h++)
    	{
    		if(ControladorFuncionario.getInstance().listar().get(h).getCPF().equals(cpf) && ControladorFuncionario.getInstance().listar().get(h) != funcionario)
    		{
    			test = 1;
    		}
    	}
    	if(test == 0) {
    	ControladorFuncionario.getInstance().atualizar(funcionario, nome, cpf, endereco);
    	}
    	else if(test == 1)
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Já existe um funcionario com esse CPF");
    		alert.show();
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Campo invalido");
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
