package main.java.br.ProjetoPizzaria.gui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.java.br.ProjetoPizzaria.exception.ElementoJaExisteException;
import main.java.br.ProjetoPizzaria.exception.ElementoNaoExisteException;
import main.java.br.ProjetoPizzaria.negocio.ControladorVenda;
import main.java.br.ProjetoPizzaria.negocio.beans.Cliente;
import main.java.br.ProjetoPizzaria.negocio.beans.Funcionario;
import main.java.br.ProjetoPizzaria.negocio.beans.Venda;

public class TelaVendasController {

    @FXML
    private Button AtualizarVendaButton;

    @FXML
    private TableColumn<Venda, Cliente> ClienteColumn;

    @FXML
    private Button AdicionarVendaButton;

    @FXML
    private Button DetalhesVendaButton;

    @FXML
    private Button ExcluirVendaButton;

    @FXML
    private TableColumn<Venda, Funcionario> FuncionarioColumn;
    
    @FXML
    private TableColumn<Venda, Double> PrecoColumn;

    @FXML
    private Button RelatorioButton;

    @FXML
    private TableColumn<Venda, LocalDateTime> TempoDaVendaColumn;

    @FXML
    private TableView<Venda> VendaTableView;

    @FXML
    private Button VoltarButton;

    @FXML
    public void initialize() throws ElementoJaExisteException 
    {
    	
    	FuncionarioColumn.setCellValueFactory(new PropertyValueFactory<Venda, Funcionario>("Funcionario"));
    	ClienteColumn.setCellValueFactory(new PropertyValueFactory<Venda, Cliente>("Cliente"));
    	TempoDaVendaColumn.setCellValueFactory(new PropertyValueFactory<Venda, LocalDateTime>("DataDeVenda"));    
    	PrecoColumn.setCellValueFactory(new PropertyValueFactory<Venda,Double>("PrecoTotal"));
    	VendaTableView.setEditable(true);
    	VendaTableView.setItems(listaDeVenda());    
    }  
    
    public ObservableList<Venda> listaDeVenda() throws ElementoJaExisteException
    {
    	ObservableList<Venda> listaDeVenda = FXCollections.observableArrayList();
    	listaDeVenda.addAll(ControladorVenda.getInstance().listar());
		return listaDeVenda;
    }
    
    @FXML
    void AtualizarVendaFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AlterarVendaTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Alterar Venda");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void AdicionarVendaFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("AdicionarVendaTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Adicionar Venda");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void DetalhesVendaFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("VendaDetalhesTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Detalhes Venda");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void ExcluirVendaFunction(ActionEvent event) throws ElementoNaoExisteException, ElementoJaExisteException {
    	
    	if(VendaTableView.getSelectionModel().getSelectedItem() != null) {
    		Alert confirm = new Alert(AlertType.CONFIRMATION,"Tem certeza que quer apagar esse objeto?");
    		Optional<ButtonType> opcao = confirm.showAndWait();
    		if(opcao.get() == null)
    		{
    			
    		}
    		else if(opcao.get() == ButtonType.OK)
    		{   
    			ControladorVenda.getInstance().remover(VendaTableView.getSelectionModel().getSelectedItem());
        	    VendaTableView.setItems(listaDeVenda()); 
        	}
    		else if(opcao.get() == ButtonType.CANCEL)
    		{
    			
    		}
    		else
    		{
    			
    		}
    		
    	}
    	
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR,"Selecione uma venda");
    		alert.show();
    	}
    	

    }

    @FXML
    void RelatorioFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("RelatorioTela.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Relatorios");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void VoltarFunction(ActionEvent event) {
    	
    	Stage stage = new Stage();
		try {
			Stage closeScreen = (Stage) VoltarButton.getScene().getWindow();
			closeScreen.close();
			Parent parent = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
			Scene scene = new Scene(parent);
			
			stage.setTitle("Menu Principal");
	    	stage.setScene(scene);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}

