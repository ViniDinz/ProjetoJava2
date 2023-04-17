package main.java.br.ProjetoPizzaria.gui;


import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
    	Pattern pattern = Pattern.compile("\\d*|\\d+\\,\\d*");
		TextFormatter<String> formatter = new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
    	    return pattern.matcher(change.getControlNewText()).matches() ? change : null;
    	});
    	PrecoAlimentoTextField.setTextFormatter(formatter);
    }
    @FXML
    void AdicionarAlimentoFunction(ActionEvent event) throws ElementoJaExisteException, ObjetoVazioException {
    	    	   	
    	if(!NomeAlimentoTextField.getText().isBlank() && !PrecoAlimentoTextField.getText().isBlank() && TipoAlimentoCB.getValue() != null)
    	{
    		int test = 0;
    		String nome = NomeAlimentoTextField.getText();
        	double preco = Double.parseDouble(PrecoAlimentoTextField.getText());       	
        	TipoAlimento tipoalimento = TipoAlimentoCB.getValue();
        	Alimento alimento = new Alimento(nome,preco,tipoalimento);
        	
        	for(int h = 0; h < ControladorAlimentos.getInstance().listar().size();h++)
        	{
        		if(ControladorAlimentos.getInstance().listar().get(h).getNome().equals(nome))
        			test = 1;
        	}
        	if(test == 0)
        	{
    		ControladorAlimentos.getInstance().inserir(alimento);
    		label1.setText("Alimento Inserido");
        	}
        	else if(test == 1)
        	{
        		Alert alert = new Alert(AlertType.ERROR,"Ja existe um alimento com esse nome");
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
			Parent parent = FXMLLoader.load(getClass().getResource("TelaAlimentos.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Alimentos");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
