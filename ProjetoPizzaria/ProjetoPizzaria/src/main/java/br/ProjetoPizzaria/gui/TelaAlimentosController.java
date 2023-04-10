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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorAlimentos;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;
import main.java.br.ProjetoPizzaria.negocio.beans.TipoAlimento;

public class TelaAlimentosController {

    @FXML
    private Button AdicionarAlimentoButton;

    @FXML
    private Button AlterarAlimentoButton;

    @FXML
    private Button ExcluirAlimentoButton;

    @FXML
    private TableView<Alimento> ListaAlimentoTBV;

    @FXML
    private Label label1;
    
    @FXML
    private TableColumn<Alimento, String> NomeAlimentosColumn;

    @FXML
    private TableColumn<Alimento, Number> PrecoAlimentosColumn;

    @FXML
    private TableColumn<Alimento, TipoAlimento> TipoAlimentosColumn;
    
    @FXML
    public void initialize() 
    {
    	
    	
    	NomeAlimentosColumn.setCellValueFactory(new PropertyValueFactory<Alimento, String>("Nome"));
        PrecoAlimentosColumn.setCellValueFactory(new PropertyValueFactory<Alimento, Number>("Preco"));
        TipoAlimentosColumn.setCellValueFactory(new PropertyValueFactory<Alimento, TipoAlimento>("Tipo"));
        
        ListaAlimentoTBV.setEditable(true);
        ListaAlimentoTBV.setItems(listaDeAlimentos());    
    }

    
    public ObservableList<Alimento> listaDeAlimentos()
    {
    	ObservableList<Alimento> listaDeAlimentos = FXCollections.observableArrayList();
    	listaDeAlimentos.addAll(ControladorAlimentos.getInstance().listar());
    	return listaDeAlimentos;
    }
    @FXML
    void AdicionarAlimentoFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) AdicionarAlimentoButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AdicionarAlimentosTela.fxml"));
			Scene scene = new Scene(parent);
			
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void AlterarAlimentoFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) AdicionarAlimentoButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AlterarAlimentosTela.fxml"));
			Scene scene = new Scene(parent);
			
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void ExcluirAlimentoFunction(ActionEvent event) throws ElementoNaoExisteException {

    	Alimento alimento = ListaAlimentoTBV.getSelectionModel().getSelectedItem();
    	
    	ControladorAlimentos.getInstance().remover(alimento);
    	ListaAlimentoTBV.setItems(listaDeAlimentos());  
    	
    }

}
