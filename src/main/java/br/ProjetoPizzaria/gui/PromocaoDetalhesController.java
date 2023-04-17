package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorPromocao;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;
import main.java.br.ProjetoPizzaria.negocio.beans.Promocao;

public class PromocaoDetalhesController {

    @FXML
    private ListView<Alimento> AlimentosListView;

    @FXML
    private ChoiceBox<Promocao> PromocaoChoiceBox;
    
    @FXML
    private Button VoltarButton;

    @FXML
    private Label labelDataFim;

    @FXML
    private Label labelDataInicio;

    @FXML
    private Label labelValor;
    
    public void initialize() throws ElementoJaExisteException 
    {
    	ObservableList<Promocao> listaDePromocao = FXCollections.observableArrayList();
    	listaDePromocao.addAll(ControladorPromocao.getInstance().listar());
        PromocaoChoiceBox.setItems(listaDePromocao);
    	
    }

    @FXML
    void VoltarFunction(ActionEvent event) {   	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaPromocao.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Promoção");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    @FXML
    void MostrarDetalhes(ActionEvent event)
    {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    	
    	labelValor.setText(Integer.toString(PromocaoChoiceBox.getValue().getValor()));
    	labelDataInicio.setText(PromocaoChoiceBox.getValue().getTempoDeInicio().format(formatador));
    	labelDataFim.setText(PromocaoChoiceBox.getValue().getTempoDeFinalizacao().format(formatador));
   	    AlimentosListView.setItems(FXCollections.observableArrayList(PromocaoChoiceBox.getValue().getProdutosValidos()));
    }

}
