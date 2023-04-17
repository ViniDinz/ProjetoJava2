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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Alimento;
import main.java.br.ProjetoPizzaria.negocio.beans.ItemVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Venda;

public class VendaDetalhesTelaController {

    @FXML
    private Label ClienteLabel;

    @FXML
    private Label FuncionarioLabel;

    @FXML
    private TableView<ItemVenda> ItemVendaTableView;

    @FXML
    private TableColumn<ItemVenda, Alimento> NomeColumn;

    @FXML
    private Label PrecoLabel;
    
    @FXML
    private Label DataLabel;

    @FXML
    private TableColumn<ItemVenda, Integer> QuantidadeColumn;

    @FXML
    private ChoiceBox<Venda> VendaCB;

    @FXML
    private Button VoltarButton;
    
    public void initialize() throws ElementoJaExisteException 
    {
    	ObservableList<Venda> listaDeVenda = FXCollections.observableArrayList();
    	listaDeVenda.addAll(ControladorVenda.getInstance().listar());
    	VendaCB.setItems(listaDeVenda);
    	
    	NomeColumn.setCellValueFactory(new PropertyValueFactory<ItemVenda, Alimento>("Alimento"));
    	QuantidadeColumn.setCellValueFactory(new PropertyValueFactory<ItemVenda, Integer>("Quantidade"));
    	ItemVendaTableView.setEditable(true);
    }

    @FXML
    void VoltarFunction(ActionEvent event) {   	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("TelaVendas.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Venda");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    @FXML
    public void MostrarDetalhes()
    {
    	 DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-uuuu");
     	
        DataLabel.setText(VendaCB.getValue().getDataDeVenda().format(formatador));
        PrecoLabel.setText(Double.toString(VendaCB.getValue().getPrecoTotal()));
        FuncionarioLabel.setText(VendaCB.getValue().getFuncionario().getNome());
        ClienteLabel.setText(VendaCB.getValue().getCliente().getNome());
        ItemVendaTableView.setItems(FXCollections.observableArrayList(VendaCB.getValue().getAlimentos()));
    }

}
