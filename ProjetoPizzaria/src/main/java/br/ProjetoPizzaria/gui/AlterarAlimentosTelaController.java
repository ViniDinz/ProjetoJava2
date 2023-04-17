package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    	
    	Pattern pattern = Pattern.compile("\\d*|\\d+\\,\\d*");
		TextFormatter<String> formatter = new TextFormatter<String>((UnaryOperator<TextFormatter.Change>) change -> {
    	    return pattern.matcher(change.getControlNewText()).matches() ? change : null;
    	});
		PrecoAlimentoTextField.setTextFormatter(formatter);
    	AlimentoCB.setItems(listaDeAlimentos);
    	TipoAlimentoCB.setItems(FXCollections.observableArrayList(TipoAlimento.values()));
    }

    @FXML
    void AlterarAlimentoFunction(ActionEvent event) throws ElementoNaoExisteException {
    	
    	if(AlimentoCB.getValue() != null && !NomeAlimentoTextField.getText().isBlank() && !PrecoAlimentoTextField.getText().isBlank()
    			&& TipoAlimentoCB.getValue() != null) {   	
    	int test = 0;
    	Alimento alimento = AlimentoCB.getValue();
    	String nome = NomeAlimentoTextField.getText();
    	double preco = Double.parseDouble(PrecoAlimentoTextField.getText());
    	int tipoAlimentoValue =  TipoAlimentoCB.getValue().getValor();   	
    	
    	for(int h = 0; h <ControladorAlimentos.getInstance().listar().size();h++)
    	{
    		if(ControladorAlimentos.getInstance().listar().get(h).getNome().equals(nome) && ControladorAlimentos.getInstance().listar().get(h) != alimento)
    			test = 1;
    	}
    	if(test == 0)
    	ControladorAlimentos.getInstance().atualizar(alimento, nome, preco, tipoAlimentoValue);
    	
    	else if(test == 1)
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Um Alimento com esse nome já existe");
   		    alert.show();
    	}
    	
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR, "Campo invalido");
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
