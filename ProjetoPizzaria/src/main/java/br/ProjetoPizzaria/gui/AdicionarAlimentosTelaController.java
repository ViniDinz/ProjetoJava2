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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ObjetoVazioException;
import main.java.br.ProjetoPizzaria.negocio.ControladorAlimentos;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;
import main.java.br.ProjetoPizzaria.negocio.beans.TipoAlimento;

public class AdicionarAlimentosTelaController {

    @FXML
    private Button AdicionarAlimentoButton;

    @FXML
    private TextField NomeAlimentoTextField;
    
    @FXML
    private Label label1;

    @FXML
    private TextField PrecoAlimentoTextField;

    @FXML
    private ChoiceBox<TipoAlimento> TipoAlimentoCB;

    @FXML
    private Button VoltarButton;

    public void initialize() 
    {   
    	TipoAlimentoCB.setItems(FXCollections.observableArrayList(TipoAlimento.values()));
    }
    @FXML
    void AdicionarAlimentoFunction(ActionEvent event) throws ElementoJaExisteException, ObjetoVazioException {
    	String nome = NomeAlimentoTextField.getText();
    	double preco = Double.parseDouble(PrecoAlimentoTextField.getText());
    	TipoAlimento tipoalimento = TipoAlimentoCB.getValue();
    	
    	Alimento alimento = new Alimento(nome,preco,tipoalimento);
    	ControladorAlimentos.getInstance().inserir(alimento);
    	
    	if(ControladorAlimentos.getInstance().listar().contains(alimento))
    	{
    		label1.setText("Alimento Inserido");
    	}
    	else
    	{
    	 label1.setText("Alimento nao inserido");
    	}

    }

    @FXML
    void VoltarFunction(ActionEvent event) {
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaAlimentos.fxml"));
			Scene scene = new Scene(parent);
			
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
