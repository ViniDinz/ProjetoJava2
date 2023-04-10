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
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorAlimentos;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;
import main.java.br.ProjetoPizzaria.negocio.beans.TipoAlimento;

public class AlterarAlimentosTelaController {

    @FXML
    private ChoiceBox<Alimento> AlimentoCB;
    
    @FXML
    private Button AlterarAlimentoButton;

    @FXML
    private TextField NomeAlimentoTextField;

    @FXML
    private TextField PrecoAlimentoTextField;

    @FXML
    private ChoiceBox<TipoAlimento> TipoAlimentoCB;

    @FXML
    private Button VoltarButton;
    
    public void initialize() 
    {
    	ObservableList<Alimento> listaDeAlimentos = FXCollections.observableArrayList();
    	listaDeAlimentos.addAll(ControladorAlimentos.getInstance().listar());	
    	
    	AlimentoCB.setItems(listaDeAlimentos);
    	TipoAlimentoCB.setItems(FXCollections.observableArrayList(TipoAlimento.values()));
    }

    @FXML
    void AlterarAlimentoFunction(ActionEvent event) throws ElementoNaoExisteException {
    	
    	Alimento alimento = AlimentoCB.getValue();
    	String nome = NomeAlimentoTextField.getText();
    	double preco = Double.parseDouble(PrecoAlimentoTextField.getText());
    	int tipoAlimentoValue =  TipoAlimentoCB.getValue().getValor();
    	
    	ControladorAlimentos.getInstance().atualizar(alimento, nome, preco, tipoAlimentoValue);

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
